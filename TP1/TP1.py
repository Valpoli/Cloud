import datetime
import json
import random
import paho.mqtt.client as paho
import time
import re
import random
import requests

# function to convert a date to a timestamp
def to_timestamp(dt):
    return int(dt.timestamp())

# open JSON file
with open('data.json', 'r') as f:
    data = json.load(f)

def find_object_by_id_and_create_tram(id, type, client):
    # loop over companies
    for company in data['companies']:
        # loop over vehicle types
        for vehicleType in company['vehicleTypes']:
           # loop over vehicles
            for vehicle in vehicleType['vehicles']:
                # generate tram if we have the correct ID
                if (vehicle['id'] == id):
                    if (type == "Alstom"):
                        return alstomTram(vehicle,vehicleType, client)
                    else:
                        return otherTram(vehicle,vehicleType, client)

def alstomTram(vehicle, vehicleType, client):
    pattern = r'^[A-Za-z0-9.]*$'
    vin = vehicle['vin']
    # Toulouse latitude/longitude
    latitude = round(random.uniform(43.5, 43.7),3)
    longitude = round(random.uniform(1.3, 1.6),4)
    # speed between 0 and 120 km/h
    allure = round(random.uniform(0, 120),1)
    date = datetime.datetime.now()
    timestamp = to_timestamp(date)
    passagers = random.randint(0, vehicleType['capacity'])
    # consumption between 30 and 150L
    consumption = round(random.uniform(30,150),1)
    vitesse = random.randint(1, 6)
    # pattern check
    if not (re.match(pattern, vin) and \
    re.match(pattern, str(latitude)) and \
    re.match(pattern, str(longitude)) and \
    re.match(pattern, str(allure)) and \
    re.match(pattern, str(passagers)) and \
    re.match(pattern, str(consumption)) and \
    re.match(pattern, str(vitesse))):
        return ""
    res = f"{vin}|{latitude}|{longitude}|{allure}|{timestamp}|{passagers}|{consumption}|{vitesse}"
    # display tram
    print(res)
    return res

def otherTram(vehicle, vehicleType, client):
    pattern = r'^[A-Za-z0-9.]*$'
    possibleMsg = [14, 56, 125]
    # randomly choose a message type
    msgType = possibleMsg[random.randint(0,2)]
    vin = vehicle['vin']
    date = datetime.datetime.now()
    timestamp = to_timestamp(date)
    res = ""
    if not re.match(pattern, vin):
        return ""
    if (msgType == 125):
        passagers = random.randint(0, vehicleType['capacity'])
        if not (re.match(pattern, str(passagers))):
            return ""
        res = f"id={vin};msg={msgType};ts={timestamp};pnb={passagers};"
    elif (msgType == 14):
        latitude = round(random.uniform(43.5, 43.7),3)
        longitude = round(random.uniform(1.3, 1.6),4)
        reservoir = round(random.uniform(0, 70),1)
        if not (re.match(pattern, str(latitude)) and \
        re.match(pattern, str(longitude)) and \
        re.match(pattern, str(reservoir))):
            return ""
        res = f"id={vin};msg={msgType};ts={timestamp};lat={latitude};long={longitude};r={reservoir};"
    else :
        allure = round(random.uniform(0, 120),1)
        consumption = round(random.uniform(30,150),1)
        vitesse = random.randint(1, 6)
        if not (re.match(pattern, str(allure)) and \
        re.match(pattern, str(consumption)) and \
        re.match(pattern, str(vitesse))):
            return ""
        res = f"id={vin};msg={msgType};ts={timestamp};a={allure};v={vitesse};c={consumption};"
    print(res)
    return res

#create list with all vehicle id possible
def getAllVehicle(type):
    idVehicle = []
    for company in data['companies']:
        # loop on vehicle types
        for vehicleType in company['vehicleTypes']:
            check = False
            #we only take the vehicle of the type we're interested in (Alstom/other)
            for e in type:
                if (e in vehicleType["manufacturer"]):
                    check = True
            if (check):
            # loop on vehicle
                for vehicle in vehicleType['vehicles']:
                    idVehicle.append(vehicle['id'])
    return idVehicle

#create list of random index
def generateRandom(size):
    num_random_numbers = data['tramNumber']
    min_number = 0
    max_number = size
    # gestion of a special case : if the ram size is bigger than the different vehicle exiting in this type, in will randomly duplicate some vehicle in the trams6
    if (num_random_numbers > size):
        random_numbers = random.sample(range(min_number, max_number), size)
        newSize = size
        while(num_random_numbers > newSize):
            random_numbers.append(random.randint(min_number, max_number - 1))
            newSize += 1
    else:
    # generate a list of random number
        random_numbers = random.sample(range(min_number, max_number), num_random_numbers)
    return random_numbers


#print random trams at 'frequencyInSec' second interval of 'tramType type (Alstom or other)
def startSending(type, tramType,client):
    idVehicle = getAllVehicle(type)
    random_numbers = generateRandom(len(idVehicle))
    res = ""
    for number in random_numbers: 
        newTram = find_object_by_id_and_create_tram(idVehicle[number], tramType,client)
        if (len(newTram)>0):
            res += newTram
            client.publish(data["topicName"],newTram)

# Function to find a random taxi and update its passenger number
def update_random_taxi():
    # Filter out the taxi vehicles
    taxi_vehicles = [
        vehicle for company in data['companies']
        for vehicleType in company['vehicleTypes']
        for vehicle in vehicleType['vehicles']
        if vehicleType['name'] == "Independant"
    ]

    if not taxi_vehicles:
        return "No taxi vehicles found in data."

    # Select a random taxi vehicle
    selected_taxi = random.choice(taxi_vehicles)

    # Generate a random number of passengers, not exceeding the taxi's capacity
    max_passengers = 6  # Assuming the capacity of a taxi is 6
    passengers = random.randint(0, max_passengers)

    # Create a timestamp for the current time
    timestamp = to_timestamp(datetime.datetime.now())

    # Prepare the data to send
    telematics_data = {
        "vehicleId": selected_taxi['vin'],
        "localTime": str(timestamp),
        "passengerNumber": passengers
    }

    print("Taxi " + selected_taxi['vin'] + " have now " + str(passengers) + " passengers")

    # Send the data to the controller
    try:
        response = requests.post("http://localhost:8081/telematics", json=telematics_data)
        return response.text
    except requests.RequestException as e:
        return f"Error sending data to controller: {e}"


# main function
def main():
    # create client object
    client = paho.Client("SSIE")
    # establish connection
    client.connect("localhost", data['port'])
    while(True):
        randomNumberTram = random.randint(0, data['numberOfMessage'])
        for i in range (randomNumberTram):
            startSending(["Alstom"], "Alstom" ,client)
        for j in range (data['numberOfMessage'] - randomNumberTram):
            startSending(["Heuliez", "EvoBus", "Poma"], "Other" ,client)
        update_random_taxi()
        print('\n')
        time.sleep(data['frequencyInSec'])
    client.disconnect()
    
main()