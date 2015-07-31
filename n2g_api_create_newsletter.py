from httplib import HTTPSConnection
from urllib import urlencode

# define your key
API_KEY = ''  

def create_newsletter(body):
    body = urlencode(body)
    headers = {"Content-type": "application/x-www-form-urlencoded",
               "Content-Length": len(body)}
    connection = HTTPSConnection('www.newsletter2go.de')
    connection.request("POST", "/de/api/create/newsletter/", body, headers)
    response = connection.getresponse()
    return response.read()

body = {'key': API_KEY,
        'name': 'test_newsletter_01',
        'type': 'email'}

print 'without "from":'
print create_newsletter(body)

body = {'key': API_KEY,
        'name': 'test_newsletter_02',
        'type': 'email',
        'from': 'dev@newsletter2go.com'}

print 'with "from":'
print create_newsletter(body)
