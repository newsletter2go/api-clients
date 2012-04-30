assert = require 'assert'
https = require 'https'
querystring = require 'querystring'

_ = require 'underscore'

path =
    sendMail: '/de/api/send/email/'

send = (path, method, params, next) ->
    body = querystring.stringify params
    options =
        host: 'www.newsletter2go.de'
        port: 443
        path: path
        method: method
        headers:
            'Content-Type': 'application/x-www-form-urlencoded'
            'Content-Length': body.length

    req = https.request options, (res) ->
        res.setEncoding 'utf8'
        raw = ''
        res.on 'data', (chunk) -> raw += chunk
        res.once 'end', -> next null, raw
    req.once 'error', (e) -> next e
    req.write body
    req.end()

safeJsonParse = (text, next) ->
    try
        json = JSON.parse text
        next null, json
    catch e
        next error: e, text: text

parseResponse = (next) -> (err, raw) ->
    safeJsonParse raw, (err, data) ->
        return next err if err?
        return next data.reason if data.success is 0
        return next null, data


module.exports = (key) ->
    assert key?, 'missing api key'
    sendMail: (params, next) ->
        params.category ?= 'basic'

        # we check _only_ the required params
        assert params.to?, 'to is required'
        assert params.from?, 'from is required'
        assert params.subject?, 'subject is required'
        assert params.html?, 'html is required'
        assert params.text?, 'text is required'
        assert _.include(['basic', 'plus'], params.category), 'category has to be either basic or plus'

        params.key = key

        send path.sendMail, 'POST', params, parseResponse next
