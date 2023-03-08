import datetime
import json
import random
import paho.mqtt.client as paho
import time
import re

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
    res = '{' + f"{vin}|{latitude}|{longitude}|{allure}|{timestamp}|{passagers}|{consumption}|{vitesse}" + '}'
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
        res = '{' + f"id={vin};msg={msgType};ts={timestamp};pnb={passagers}" + '}'
    elif (msgType == 14):
        latitude = round(random.uniform(43.5, 43.7),3)
        longitude = round(random.uniform(1.3, 1.6),4)
        reservoir = round(random.uniform(0, 70),1)
        if not (re.match(pattern, str(latitude)) and \
        re.match(pattern, str(longitude)) and \
        re.match(pattern, str(reservoir))):
            return ""
        res = '{' + f"id={vin};msg={msgType};ts={timestamp};lat={latitude};long={longitude};r={reservoir}" + '}'
    else :
        allure = round(random.uniform(0, 120),1)
        consumption = round(random.uniform(30,150),1)
        vitesse = random.randint(1, 6)
        if not (re.match(pattern, str(allure)) and \
        re.match(pattern, str(consumption)) and \
        re.match(pattern, str(vitesse))):
            return ""
        res = '{' + f"id={vin};msg={msgType};ts={timestamp};a={allure};v={vitesse};c={consumption}" + '}'
    print(res)
    return res

#create list with all vehicule id possible
def getAllVehicle(type):
    idVehicle = []
    for company in data['companies']:
        # loop on vehicule types
        for vehicleType in company['vehicleTypes']:
            check = False
            #we only take the vehicule of the type we're interested in (Alstom/other)
            for e in type:
                if (e in vehicleType["manufacturer"]):
                    check = True
            if (check):
            # loop on vehicule
                for vehicle in vehicleType['vehicles']:
                    idVehicle.append(vehicle['id'])
    return idVehicle

#create list of random index
def generateRandom(size):
    num_random_numbers = data['tramNumber']
    min_number = 0
    max_number = size
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


#print 'numberOfMessage' random trams at 'frequencyInSec' second interval of 'tramType type (Alstom or other)
def startSending(numberOfMessage, type, tramType,client):
    idVehicle = getAllVehicle(type)
    for i in range(numberOfMessage):
        random_numbers = generateRandom(len(idVehicle))
        res = ""
        for number in random_numbers: 
            newTram = find_object_by_id_and_create_tram(idVehicle[number], tramType,client)
            if (len(newTram)>0):
                res += newTram
        client.publish(data["topicName"], "val_" + res)
        print('\n')
        time.sleep(data['frequencyInSec'])


#create client and start publishing 5 messages for each tram type
def main():
    # create client object
    client = paho.Client("SSIE")
    # establish connection
    client.connect("localhost", 1883)
    startSending(5, ["Alstom"], "Alstom" ,client)
    startSending(5, ["Heuliez", "EvoBus", "Poma"], "Other" ,client)
    client.disconnect()

main()