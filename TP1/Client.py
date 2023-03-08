import paho.mqtt.client as paho

def on_connect(client, userdata, flags, rc):
    print("Connected to broker with result code "+str(rc))
    # subscribe to topic_ssie when connected
    client.subscribe("topic_ssie")

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
    client.connect("localhost", 1883)
    # start the network loop
    client.loop_forever()
    
readMsg()