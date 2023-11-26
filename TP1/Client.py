import paho.mqtt.client as paho
import json

# open JSON file
with open('data.json', 'r') as f:
    data = json.load(f)

def on_connect(client, userdata, flags, rc):
    print("Connected to broker with result code "+str(rc))
    # subscribe to topic_ssie when connected
    client.subscribe(data["topicName"])

def on_message(client, userdata, msg):
    print("Received message '"+str(msg.payload)+"' on topic '"+
          msg.topic+"' with QoS "+str(msg.qos))
    
def readMsg():
    # create client object
    client = paho.Client("CLIENT")
    # set callbacks
    client.on_connect = on_connect
    client.on_message = on_message
    # establish connection
    client.connect("localhost", data['port'])
    # start the network loop
    client.loop_forever()
    
readMsg()