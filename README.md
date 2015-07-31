# api-clients
Examples for using the Newsletter2go API

Die aktuelle API Dokumentation befindet sich hier:
https://www.newsletter2go.de/pr/api/Newsletter2Go_API_Doku_latest.pdf

Beispiele gibt es für folgende Sprachen, jeweils als eigenen Branch:
- [Java](../../tree/java)
- [PHP](../../tree/php)
- [Python](../../tree/python)

Bei Fragen können Sie sich an dev (at) newsletter2go.com wenden.


# newsletter2go.de api implementation written in coffee-script

This is Example is written by [Mark Engel](https://github.com/mren)

Currently this example only implements the send mails functionality.

Feel free to contribute.

# Usage

```coffeescript
#!/usr/bin/env coffee
Newsletter2Go = require './newsletter2go'

key = 'YOUR API KEY'

api = Newsletter2Go key

params =
    to: 'to@example.com'
    from: 'from@example.com'
    subject: 'subject line'
    html: '<h1>headline</h2><p>paragraph</p>'
    text: 'this is a testmail'

api.sendMail params, console.log
```
