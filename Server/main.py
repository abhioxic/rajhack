from contextlib import closing
from urllib.parse import urlencode
from urllib.request import urlopen
import urllib.request
import json
import codecs
import  pymysql.cursors
from flask import Flask,request
import urllib 
import http.client
import requests
import base64
import gzip
import random
dbb = pymysql.connect(host="localhost",    # your host, usually localhost
                      user="root",         # your username
                      passwd="",  # your password
                      db="rjhck")
cursormain = dbb.cursor()


def encoder(c):
    return base64.b64encode(gzip.compress(c))

def decoder(d):
    return gzip.decompress(base64.b64decode(d))


class DB:
    conn = None
    cursor = None

    def connect(self):
        self.conn = dbb
        self.cursor = cursormain

    def query(self, sql):
        try:
            self.cursor.execute(sql)
        except (AttributeError):
            self.connect()
            self.cursor = self.conn.cursor()
            self.cursor.execute(sql)
        dbb.commit()


    def fetch(self,sql):
        try:
            self.cursor.execute(sql)
        except (AttributeError):
            self.connect()
            self.cursor = self.conn.cursor()
            self.cursor.execute(sql)
        ret =  self.cursor.fetchall()
        return ret


app = Flask(__name__)

@app.route('/',methods=['GET', 'POST'])
def hello_world():
    print(request.data)
    print(str(request.data,'utf-8'))
    data=json.loads(str(request.data,'utf-8'))
    print(data)
    db = DB()
    if data["type"]=="gotsms":
        sender = data['num']
        content = data['msg']
        content=content[5:].replace("'","\"")

        print(content)
        print(sender,content)
        d = json.loads(content)
        cityid = d["c"]
        k="SELECT `city` FROM `city` WHERE `cityid`="+str(cityid)
        qresult = db.fetch(k)
        city=qresult[0][0]
        print("City :",city)
        print("Veggies :")
        k="SELECT cv.`Min`,cv.`Avg`,cv.`Max` FROM `city_veg_price` cv,`veg` v WHERE v.`VegId`=cv.`VegId` AND cv.`city`="+str(cityid)
        print(k)
        qresult = db.fetch(k)
        print(qresult)
        alldata = []
        for i in qresult:
            temp=[]
            for j in i:
                temp.append(j)
            alldata.append(temp)
            # veg.append(i[0])
        datatosend={"veg":alldata}
        print(json.dumps({'data':datatosend,"num":sender}))
        return json.dumps({'data':datatosend}), 200, {'ContentType':'application/json'}

    if data["type"]=="checknotification":
        k="SELECT * FROM `notifications` WHERE `done`=0"
        qr = db.fetch(k)
        print(qr)
        if len(qr)>0:
            k="UPDATE `notifications` SET `done`=1 WHERE `srno`="+str(qr[0][0])
            db.query(k)
            k="SELECT * FROM `users`"
            qrr = db.fetch(k)
            mob=[]
            for i in qrr:
                mob.append(i[1])
            datatosend={"msg":qr[0][2],"mob":mob}
        else:
            datatosend={"msg":"none","mob":[]}
        print(datatosend)
        return json.dumps(datatosend), 200, {'ContentType':'application/json'}

    if data["type"]=="sendnotification":
        msg = data["msg"]
        k="INSERT INTO `notifications`(`catagory`, `msg`, `time`, `done`) VALUES ('all','"+msg+"',now(),0)"
        db.query(k)
        return json.dumps({'success':"true"}), 200, {'ContentType':'application/json'}

    if data["type"]=="checklogin":

        sender = data['num']
        content = data['msg']
        content=content[5:].replace("'","\"")

        print(content)
        print(sender,content)
        d = json.loads(r''.join(content))   

        bhama = d["bhama"]
        adhar = d["adhar"]
        if bhama != "":
            k="SELECT * FROM `bhamashah_users` WHERE `bhamashahid`='"+bhama+"'"
            qr = db.fetch(k)
            if len(qr) == 0:
                return "RJLOG"+json.dumps({"info":["fail"]}), 200, {'ContentType':'application/json'}
            temp=[]
            for i in qr[0]:
                temp.append(i)
            datatosend={"info":temp}
            return "RJLOG"+json.dumps(datatosend), 200, {'ContentType':'application/json'}
        elif adhar!="":
            print("here")
            k="SELECT * FROM `bhamashah_users` WHERE `adharid`='"+adhar+"'"
            qr = db.fetch(k)
            if len(qr) == 0:
                return "RJLOG"+json.dumps({"info":["fail"]}), 200, {'ContentType':'application/json'}
            temp=[]
            for i in qr[0]:
                temp.append(i)
            datatosend={"info":temp}
            return "RJLOG"+json.dumps(datatosend), 200, {'ContentType':'application/json'}
        else:
            return "RJLOG"+json.dumps({"info":["fail"]}), 200, {'ContentType':'application/json'}

    if data["type"]=="sendotp":
        phone=data["phone"]
        rand = random.randint(1000,9999)
        k="INSERT INTO `otps`( `phone`, `otp`) VALUES ('"+phone+"',"+rand+")"
        db.query(k)
        return json.dumps({"msg":"OTP is "+rand,"phone":phone}),200, {'ContentType':'application/json'}

    if data["type"]=="appointmentaccept":
        content = data["msg"]
        content=content.replace("'","\"")
        d = json.loads(r''.join(content))   
        print(d)
        addr = d['addr']
        content = d['date']
        timer = d['timer']
        print(addr,content,timer)
        return json.dumps({'success':"true"}), 200, {'ContentType':'application/json'}
        # print()

    if data["type"]=="fetchreport":
        pass

if __name__ == '__main__':
    app.run(host='0.0.0.0',port=7824,debug=True,threaded=True)