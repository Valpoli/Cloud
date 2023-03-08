import datetime
import json
import random
import paho.mqtt.client as paho
import time
import re

# fonction pour convertir une date en timestamp
def to_timestamp(dt):
    return int(dt.timestamp())

# ouverture du fichier JSON
with open('data.json', 'r') as f:
    data = json.load(f)

def find_object_by_id_and_create_tram(id, type, client):
    # boucle sur les entreprises
    for company in data['companies']:
        # boucle sur les types de véhicules
        for vehicleType in company['vehicleTypes']:
            # boucle sur les véhicules
            for vehicle in vehicleType['vehicles']:
                # génération de la tram si on est sur le bon ID
                if (vehicle['id'] == id):
                    if (type == "Alstom"):
                        return alstomTram(vehicle,vehicleType, client)
                    else:
                        return otherTram(vehicle,vehicleType, client)

def alstomTram(vehicle, vehicleType, client):
    pattern = r'^[A-Za-z0-9.]*$'
    vin = vehicle['vin']
    # latitude/longitude toulouse
    latitude = round(random.uniform(43.5, 43.7),3)
    longitude = round(random.uniform(1.3, 1.6),4)
    # entre 0 et 120 km/h
    allure = round(random.uniform(0, 120),1)
    date = datetime.datetime.now()
    timestamp = to_timestamp(date)
    passagers = random.randint(0, vehicleType['capacity'])
    # entre 30 et 150L
    consommation = round(random.uniform(30,150),1)
    vitesse = random.randint(1, 6)
    # affichage de la tram
    if not (re.match(pattern, vin) and \
    re.match(pattern, str(latitude)) and \
    re.match(pattern, str(longitude)) and \
    re.match(pattern, str(allure)) and \
    re.match(pattern, str(passagers)) and \
    re.match(pattern, str(consommation)) and \
    re.match(pattern, str(vitesse))):
        return ""
    res = '{' + f"{vin}|{latitude}|{longitude}|{allure}|{timestamp}|{passagers}|{consommation}|{vitesse}" + '}'
    print(res)
    return res

def otherTram(vehicle, vehicleType, client):
    pattern = r'^[A-Za-z0-9.]*$'
    possibleMsg = [14, 56, 125]
    # choisi un type de message aléatoirement
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
        consommation = round(random.uniform(30,150),1)
        vitesse = random.randint(1, 6)
        if not (re.match(pattern, str(allure)) and \
        re.match(pattern, str(consommation)) and \
        re.match(pattern, str(vitesse))):
            return ""
        res = '{' + f"id={vin};msg={msgType};ts={timestamp};a={allure};v={vitesse};c={consommation}" + '}'
    print(res)
    return res

#créer une list avec tous les id de véhicule possible
def getAllVehicle():
    idVehicle = []
    for company in data['companies']:
        # boucle sur les types de véhicules
        for vehicleType in company['vehicleTypes']:
            # boucle sur les véhicules
            for vehicle in vehicleType['vehicles']:
                idVehicle.append(vehicle['id'])
    return idVehicle

#créer une liste d'indice aléatoire
def generateRandom(size):
    num_random_numbers = data['tramNumber']
    min_number = 0
    max_number = size
    # Genère une liste de nombre aléatoire
    random_numbers = random.sample(range(min_number, max_number), num_random_numbers)
    return random_numbers

#affiche 10 tram aléatoire a 'frequencyInSec' secondes d'interval, 5 version Alstom et 5 version Heuliez, EvoBus, Poma
def main():
    # create client object
    client = paho.Client("SSIE")
    # establish connection
    client.connect("localhost", 1883)
    # start the network loop
    idVehicle = getAllVehicle()
    random_numbers = generateRandom(len(idVehicle))
    for i in range(5):
        res = ""
        for number in random_numbers: 
            newTram = find_object_by_id_and_create_tram(idVehicle[number], "Alstom",client)
            if (len(newTram)>0):
                res += newTram
        client.publish("topic_ssie", "val_" + res)
        print('\n')
        time.sleep(data['frequencyInSec'])
    for i in range(5):
        res = ""
        for number in random_numbers: 
            newTram = find_object_by_id_and_create_tram(idVehicle[number], "Other", client)
            if (len(newTram)>0):
                res += newTram
        client.publish("topic_ssie", "val_" + res)
        print('\n')
        time.sleep(data['frequencyInSec'])
    client.disconnect()

main()