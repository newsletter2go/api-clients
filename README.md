newsletter2go.de api implementation written in coffee-script
============================================================

newsletter2go.de is a german online newsletter sender.

The API is documented on [the website](https://www.newsletter2go.de/pr/api/Newsletter2Go_API_Doku_v1.4.pdf).

Currently this implementation only implements to send mails.

Feel free to contribute.

Usage
=====

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
