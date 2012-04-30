newsletter2go.de api implementation written in coffee-script
============================================================

[newsletter2go.de](http://www.newsletter2go.de) is a german online newsletter software.

You can find the [API documentation](https://www.newsletter2go.de/de/api) on the website.

Currently this implementation only implements the send mails functionality.

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
