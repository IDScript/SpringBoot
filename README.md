# Spring Boot RestFul API

[![Coverage](.github/badges/jacoco.svg)](https://github.com/IDScript/SpringBoot/tree/dev/.github/workflows/UnitTest.yml) [![Branch](.github/badges/branches.svg)](https://github.com/IDScript/SpringBoot/tree/dev/.github/workflows/UnitTest.yml)

Spring Boot Restful API

---
title: Spring Boot API v0.0.1-alpha
language_tabs:

- shell: Shell
- http: HTTP
- javascript: JavaScript
- ruby: Ruby
- python: Python
- php: PHP
- java: Java
- go: Go
toc_footers:
- <a href="https://spring.idscript.com/docs">Find out more about WhatsAPI</a>
includes: []
search: true
highlight_theme: darkula
headingLevel: 2

---

<!-- Generator: Widdershins v4.0.1 -->

<h1 id="spring-boot-api">Spring Boot API v0.0.1-alpha</h1>

> Scroll down for code samples, example requests and responses. Select a language for code samples from the tabs above or the mobile navigation menu.

Spring Boot Restful API

Base URLs:

- <a href="https://spring.idscript.com/{environment}/v1">https://spring.idscript.com/{environment}/v1</a>

  - **environment** - Server Environment Default: api

    - dev

    - test

    - api

Email: <a href="mailto:spring@idscript.com">IDScript</a> Web: <a href="https://spring.idscript.com">IDScript</a>
License: <a href="https://spring.idscript.com/LICENSE.md">MIT</a>

# Authentication

- HTTP Authentication, scheme: bearer Authentication with user token

<h1 id="spring-boot-api-user-management">User Management</h1>

## post__users

> Code samples

```shell
# You can also use wget
curl -X POST https://spring.idscript.com/{environment}/v1/users \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST https://spring.idscript.com/{environment}/v1/users HTTP/1.1
Host: spring.idscript.com
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "name": "example name",
  "username": "example",
  "password": "Secret"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/users',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'https://spring.idscript.com/{environment}/v1/users',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('https://spring.idscript.com/{environment}/v1/users', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','https://spring.idscript.com/{environment}/v1/users', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/users");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "https://spring.idscript.com/{environment}/v1/users", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /users`

*User Registration*

Endpoint for user Registration

> Body parameter

> User Data for registeration

```json
{
  "name": "example name",
  "username": "example",
  "password": "Secret"
}
```

<h3 id="post__users-parameters">Parameters</h3>

| Name       | In   | Type                                  | Required | Description |
| ---------- | ---- | ------------------------------------- | -------- | ----------- |
| body       | body | object                                | true     | User Data   |
| » username | body | [defaultString](#schemadefaultstring) | false    | none        |
| » password | body | [defaultString](#schemadefaultstring) | false    | none        |
| » name     | body | [defaultString](#schemadefaultstring) | false    | none        |

> Example responses

> Default User Response

```json
{
  "message": "OK",
  "data": {
    "token": "jncksdc7bdjb6r7ausvb",
    "name": "example name",
    "username": "example"
  },
  "meta": {
    "location": "https://api/v1/user/1"
  }
}
```

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="post__users-responses">Responses</h3>

| Status  | Meaning                                                          | Description | Schema                    |
| ------- | ---------------------------------------------------------------- | ----------- | ------------------------- |
| 201     | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)     | Created     | [userRes](#schemauserres) |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request | [error](#schemaerror)     |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found   | [error](#schemaerror)     |
| default | Default                                                          | OK          | [userRes](#schemauserres) |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## get__users

> Code samples

```shell
# You can also use wget
curl -X GET https://spring.idscript.com/{environment}/v1/users \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://spring.idscript.com/{environment}/v1/users HTTP/1.1
Host: spring.idscript.com
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/users',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://spring.idscript.com/{environment}/v1/users',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://spring.idscript.com/{environment}/v1/users', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://spring.idscript.com/{environment}/v1/users', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/users");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://spring.idscript.com/{environment}/v1/users", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /users`

*User List*

Endpoint for show Users List

> Example responses

> Default User Response

```json
{
  "message": "Success.",
  "data": {
    "users": [
      {
        "name": "example name",
        "username": "example"
      },
      {
        "name": "example name",
        "username": "example"
      },
      {
        "name": "example name",
        "username": "example"
      },
      {
        "name": "example name",
        "username": "example"
      }
    ]
  },
  "meta": {}
}
```

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="get__users-responses">Responses</h3>

| Status  | Meaning                                                          | Description  | Schema                    |
| ------- | ---------------------------------------------------------------- | ------------ | ------------------------- |
| 200     | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | OK           | Inline                    |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request  | [error](#schemaerror)     |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized | [error](#schemaerror)     |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden    | [error](#schemaerror)     |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found    | [error](#schemaerror)     |
| default | Default                                                          | OK           | [userRes](#schemauserres) |

<h3 id="get__users-responseschema">Response Schema</h3>

Status Code **200**

| Name         | Type                                  | Required | Restrictions | Description |
| ------------ | ------------------------------------- | -------- | ------------ | ----------- |
| » message    | [defaultString](#schemadefaultstring) | false    | none         | none        |
| » data       | object                                | true     | none         | none        |
| »» users     | [object]                              | false    | none         | none        |
| »»» name     | [defaultString](#schemadefaultstring) | false    | none         | none        |
| »»» username | [defaultString](#schemadefaultstring) | false    | none         | none        |
| » meta       | object                                | false    | none         | none        |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## get__user_{user_id}

> Code samples

```shell
# You can also use wget
curl -X GET https://spring.idscript.com/{environment}/v1/user/{user_id} \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://spring.idscript.com/{environment}/v1/user/{user_id} HTTP/1.1
Host: spring.idscript.com
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/user/{user_id}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://spring.idscript.com/{environment}/v1/user/{user_id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://spring.idscript.com/{environment}/v1/user/{user_id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://spring.idscript.com/{environment}/v1/user/{user_id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/user/{user_id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://spring.idscript.com/{environment}/v1/user/{user_id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /user/{user_id}`

*Get User Data*

Endpoint for Get User Data

<h3 id="get__user_{user_id}-parameters">Parameters</h3>

| Name    | In   | Type                                  | Required | Description |
| ------- | ---- | ------------------------------------- | -------- | ----------- |
| user_id | path | [defaultString](#schemadefaultstring) | true     | User ID     |

> Example responses

> Default User Response

```json
{
  "message": "OK",
  "data": {
    "token": "jncksdc7bdjb6r7ausvb",
    "name": "example name",
    "username": "example"
  },
  "meta": {
    "location": "https://api/v1/user/1"
  }
}
```

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="get__user_{user_id}-responses">Responses</h3>

| Status  | Meaning                                                          | Description  | Schema                    |
| ------- | ---------------------------------------------------------------- | ------------ | ------------------------- |
| 200     | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | OK           | [userRes](#schemauserres) |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request  | [error](#schemaerror)     |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized | [error](#schemaerror)     |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden    | [error](#schemaerror)     |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found    | [error](#schemaerror)     |
| default | Default                                                          | OK           | [userRes](#schemauserres) |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## patch__user_{user_id}

> Code samples

```shell
# You can also use wget
curl -X PATCH https://spring.idscript.com/{environment}/v1/user/{user_id} \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
PATCH https://spring.idscript.com/{environment}/v1/user/{user_id} HTTP/1.1
Host: spring.idscript.com
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "name": "New Name"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/user/{user_id}',
{
  method: 'PATCH',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.patch 'https://spring.idscript.com/{environment}/v1/user/{user_id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.patch('https://spring.idscript.com/{environment}/v1/user/{user_id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PATCH','https://spring.idscript.com/{environment}/v1/user/{user_id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/user/{user_id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PATCH");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PATCH", "https://spring.idscript.com/{environment}/v1/user/{user_id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PATCH /user/{user_id}`

*Update User Data*

Endpoint for Update User Data

> Body parameter

> Update name

```json
{
  "name": "New Name"
}
```

<h3 id="patch__user_{user_id}-parameters">Parameters</h3>

| Name    | In   | Type                                  | Required | Description                       |
| ------- | ---- | ------------------------------------- | -------- | --------------------------------- |
| user_id | path | [defaultString](#schemadefaultstring) | true     | User ID                           |
| body    | body | [userUpdate](#schemauserupdate)       | true     | Data dari user yang ingin update. |

> Example responses

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="patch__user_{user_id}-responses">Responses</h3>

| Status  | Meaning                                                          | Description  | Schema                                        |
| ------- | ---------------------------------------------------------------- | ------------ | --------------------------------------------- |
| 200     | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | OK           | [defaultUserSchema](#schemadefaultuserschema) |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request  | [error](#schemaerror)                         |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized | [error](#schemaerror)                         |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden    | [error](#schemaerror)                         |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found    | [error](#schemaerror)                         |
| default | Default                                                          | OK           | [userRes](#schemauserres)                     |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## post__auth_login

> Code samples

```shell
# You can also use wget
curl -X POST https://spring.idscript.com/{environment}/v1/auth/login \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST https://spring.idscript.com/{environment}/v1/auth/login HTTP/1.1
Host: spring.idscript.com
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "username": "example",
  "password": "Secret"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/auth/login',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'https://spring.idscript.com/{environment}/v1/auth/login',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('https://spring.idscript.com/{environment}/v1/auth/login', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','https://spring.idscript.com/{environment}/v1/auth/login', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/auth/login");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "https://spring.idscript.com/{environment}/v1/auth/login", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /auth/login`

*User Login*

Endpoint for User Login

> Body parameter

> Login request body

```json
{
  "username": "example",
  "password": "Secret"
}
```

<h3 id="post__auth_login-parameters">Parameters</h3>

| Name | In   | Type                  | Required | Description |
| ---- | ---- | --------------------- | -------- | ----------- |
| body | body | [login](#schemalogin) | true     | User Data.  |

> Example responses

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="post__auth_login-responses">Responses</h3>

| Status  | Meaning                                                          | Description  | Schema                                        |
| ------- | ---------------------------------------------------------------- | ------------ | --------------------------------------------- |
| 200     | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | Created      | [defaultUserSchema](#schemadefaultuserschema) |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request  | [error](#schemaerror)                         |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized | [error](#schemaerror)                         |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden    | [error](#schemaerror)                         |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found    | [error](#schemaerror)                         |
| default | Default                                                          | OK           | [userRes](#schemauserres)                     |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## delete__auth_logout

> Code samples

```shell
# You can also use wget
curl -X DELETE https://spring.idscript.com/{environment}/v1/auth/logout \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
DELETE https://spring.idscript.com/{environment}/v1/auth/logout HTTP/1.1
Host: spring.idscript.com
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/auth/logout',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.delete 'https://spring.idscript.com/{environment}/v1/auth/logout',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.delete('https://spring.idscript.com/{environment}/v1/auth/logout', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','https://spring.idscript.com/{environment}/v1/auth/logout', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/auth/logout");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "https://spring.idscript.com/{environment}/v1/auth/logout", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /auth/logout`

*User LogOut*

Endpoint for User Logout

> Example responses

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="delete__auth_logout-responses">Responses</h3>

| Status  | Meaning                                                          | Description                           | Schema                    |
| ------- | ---------------------------------------------------------------- | ------------------------------------- | ------------------------- |
| 204     | [No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)  | The resource was deleted successfully | None                      |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request                           | [error](#schemaerror)     |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized                          | [error](#schemaerror)     |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden                             | [error](#schemaerror)     |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found                             | [error](#schemaerror)     |
| default | Default                                                          | OK                                    | [userRes](#schemauserres) |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="spring-boot-api-contact-management">Contact Management</h1>

## post__contacts

> Code samples

```shell
# You can also use wget
curl -X POST https://spring.idscript.com/{environment}/v1/contacts \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST https://spring.idscript.com/{environment}/v1/contacts HTTP/1.1
Host: spring.idscript.com
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "firstName": "firstName",
  "lastName": "lastName",
  "email": "email@email.com",
  "phone": "1234567890"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/contacts',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'https://spring.idscript.com/{environment}/v1/contacts',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('https://spring.idscript.com/{environment}/v1/contacts', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','https://spring.idscript.com/{environment}/v1/contacts', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/contacts");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "https://spring.idscript.com/{environment}/v1/contacts", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /contacts`

*Add User Contact*

Endpoint for Add User Contact

> Body parameter

> User Data for Add User Contact

```json
{
  "firstName": "firstName",
  "lastName": "lastName",
  "email": "email@email.com",
  "phone": "1234567890"
}
```

<h3 id="post__contacts-parameters">Parameters</h3>

| Name | In   | Type                            | Required | Description       |
| ---- | ---- | ------------------------------- | -------- | ----------------- |
| body | body | [contactReq](#schemacontactreq) | true     | User Contact Data |

> Example responses

> Success Add Contact

```json
{
  "message": "User Created",
  "data": {
    "id": "random string for id contact",
    "firstName": "firstName",
    "lastName": "lastName",
    "email": "email@email.com",
    "phone": "1234567890"
  },
  "meta": {
    "location": "https://api/v1/user/1"
  }
}
```

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="post__contacts-responses">Responses</h3>

| Status  | Meaning                                                          | Description  | Schema                          |
| ------- | ---------------------------------------------------------------- | ------------ | ------------------------------- |
| 201     | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)     | Created      | [contactRes](#schemacontactres) |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request  | [error](#schemaerror)           |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized | [error](#schemaerror)           |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden    | [error](#schemaerror)           |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found    | [error](#schemaerror)           |
| default | Default                                                          | OK           | [userRes](#schemauserres)       |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## get__contacts

> Code samples

```shell
# You can also use wget
curl -X GET https://spring.idscript.com/{environment}/v1/contacts \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://spring.idscript.com/{environment}/v1/contacts HTTP/1.1
Host: spring.idscript.com
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "name": "firstName or/and lastname",
  "email": "email@email.com",
  "phone": "1234567890"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/contacts',
{
  method: 'GET',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://spring.idscript.com/{environment}/v1/contacts',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://spring.idscript.com/{environment}/v1/contacts', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://spring.idscript.com/{environment}/v1/contacts', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/contacts");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://spring.idscript.com/{environment}/v1/contacts", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /contacts`

*Search Contact*

Endpoint for Search Contact

> Body parameter

> User Data for Search User Contact

```json
{
  "name": "firstName or/and lastname",
  "email": "email@email.com",
  "phone": "1234567890"
}
```

<h3 id="get__contacts-parameters">Parameters</h3>

| Name | In   | Type                            | Required | Description              |
| ---- | ---- | ------------------------------- | -------- | ------------------------ |
| body | body | [contactReq](#schemacontactreq) | true     | Search User Contact Data |

> Example responses

> Default Contact Response

```json
{
  "message": "Success.",
  "data": {
    "id": "random string for id contact",
    "firstName": "firstName",
    "lastName": "lastName",
    "email": "email@email.com",
    "phone": "1234567890"
  },
  "meta": {
    "location": "http://api/contacts/1"
  }
}
```

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="get__contacts-responses">Responses</h3>

| Status  | Meaning                                                          | Description  | Schema                          |
| ------- | ---------------------------------------------------------------- | ------------ | ------------------------------- |
| 200     | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | OK           | [contactRes](#schemacontactres) |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request  | [error](#schemaerror)           |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized | [error](#schemaerror)           |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden    | [error](#schemaerror)           |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found    | [error](#schemaerror)           |
| default | Default                                                          | OK           | [userRes](#schemauserres)       |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## get__contacts_{contact_id}

> Code samples

```shell
# You can also use wget
curl -X GET https://spring.idscript.com/{environment}/v1/contacts/{contact_id} \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://spring.idscript.com/{environment}/v1/contacts/{contact_id} HTTP/1.1
Host: spring.idscript.com
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://spring.idscript.com/{environment}/v1/contacts/{contact_id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://spring.idscript.com/{environment}/v1/contacts/{contact_id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/contacts/{contact_id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://spring.idscript.com/{environment}/v1/contacts/{contact_id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /contacts/{contact_id}`

*Get Contact Data*

Endpoint for show Contact Data

<h3 id="get__contacts_{contact_id}-parameters">Parameters</h3>

| Name       | In   | Type                                  | Required | Description |
| ---------- | ---- | ------------------------------------- | -------- | ----------- |
| contact_id | path | [defaultString](#schemadefaultstring) | true     | Contact ID  |

> Example responses

> Default Contact Response

```json
{
  "message": "Success.",
  "data": {
    "id": "random string for id contact",
    "firstName": "firstName",
    "lastName": "lastName",
    "email": "email@email.com",
    "phone": "1234567890"
  },
  "meta": {
    "location": "http://api/contacts/1"
  }
}
```

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="get__contacts_{contact_id}-responses">Responses</h3>

| Status  | Meaning                                                          | Description  | Schema                          |
| ------- | ---------------------------------------------------------------- | ------------ | ------------------------------- |
| 200     | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | OK           | [contactRes](#schemacontactres) |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request  | [error](#schemaerror)           |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized | [error](#schemaerror)           |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden    | [error](#schemaerror)           |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found    | [error](#schemaerror)           |
| default | Default                                                          | OK           | [userRes](#schemauserres)       |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## put__contacts_{contact_id}

> Code samples

```shell
# You can also use wget
curl -X PUT https://spring.idscript.com/{environment}/v1/contacts/{contact_id} \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
PUT https://spring.idscript.com/{environment}/v1/contacts/{contact_id} HTTP/1.1
Host: spring.idscript.com
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "firstName": "old firstName",
  "lastName": "old lastName",
  "email": "new email@email.com",
  "phone": "old 1234567890"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}',
{
  method: 'PUT',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.put 'https://spring.idscript.com/{environment}/v1/contacts/{contact_id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.put('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','https://spring.idscript.com/{environment}/v1/contacts/{contact_id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/contacts/{contact_id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "https://spring.idscript.com/{environment}/v1/contacts/{contact_id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /contacts/{contact_id}`

*Update User Contact*

Endpoint for Update User Contact

> Body parameter

> User Data for Update User Contact

```json
{
  "firstName": "old firstName",
  "lastName": "old lastName",
  "email": "new email@email.com",
  "phone": "old 1234567890"
}
```

<h3 id="put__contacts_{contact_id}-parameters">Parameters</h3>

| Name       | In   | Type                                  | Required | Description       |
| ---------- | ---- | ------------------------------------- | -------- | ----------------- |
| contact_id | path | [defaultString](#schemadefaultstring) | true     | Contact ID        |
| body       | body | [contactReq](#schemacontactreq)       | true     | User Contact Data |

> Example responses

> Success Update Contact

```json
{
  "message": "User Created",
  "data": {
    "id": "random string for id contact",
    "firstName": "firstName",
    "lastName": "lastName",
    "email": "email@email.com",
    "phone": "1234567890"
  },
  "meta": {
    "location": "https://api/v1/user/1"
  }
}
```

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="put__contacts_{contact_id}-responses">Responses</h3>

| Status  | Meaning                                                          | Description  | Schema                          |
| ------- | ---------------------------------------------------------------- | ------------ | ------------------------------- |
| 200     | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | Updated      | [contactRes](#schemacontactres) |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request  | [error](#schemaerror)           |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized | [error](#schemaerror)           |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden    | [error](#schemaerror)           |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found    | [error](#schemaerror)           |
| default | Default                                                          | OK           | [userRes](#schemauserres)       |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## delete__contacts_{contact_id}

> Code samples

```shell
# You can also use wget
curl -X DELETE https://spring.idscript.com/{environment}/v1/contacts/{contact_id} \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
DELETE https://spring.idscript.com/{environment}/v1/contacts/{contact_id} HTTP/1.1
Host: spring.idscript.com
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.delete 'https://spring.idscript.com/{environment}/v1/contacts/{contact_id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.delete('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','https://spring.idscript.com/{environment}/v1/contacts/{contact_id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/contacts/{contact_id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "https://spring.idscript.com/{environment}/v1/contacts/{contact_id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /contacts/{contact_id}`

*Delete Contact Data*

Endpoint for delete Contact Data

<h3 id="delete__contacts_{contact_id}-parameters">Parameters</h3>

| Name       | In   | Type                                  | Required | Description |
| ---------- | ---- | ------------------------------------- | -------- | ----------- |
| contact_id | path | [defaultString](#schemadefaultstring) | true     | Contact ID  |

> Example responses

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="delete__contacts_{contact_id}-responses">Responses</h3>

| Status  | Meaning                                                          | Description                           | Schema                    |
| ------- | ---------------------------------------------------------------- | ------------------------------------- | ------------------------- |
| 204     | [No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)  | The resource was deleted successfully | None                      |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request                           | [error](#schemaerror)     |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized                          | [error](#schemaerror)     |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden                             | [error](#schemaerror)     |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found                             | [error](#schemaerror)     |
| default | Default                                                          | OK                                    | [userRes](#schemauserres) |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="spring-boot-api-address-management">Address Management</h1>

## post__contacts_{contact_id}_addresses

> Code samples

```shell
# You can also use wget
curl -X POST https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses HTTP/1.1
Host: spring.idscript.com
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "street": "Jalan apa",
  "city": "Kota mana",
  "country": "Negara",
  "province": "Provinsi",
  "postalCode": 112233
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /contacts/{contact_id}/addresses`

*Add User Address*

Endpoint for Add User Address

> Body parameter

> User Data for Add User Address

```json
{
  "street": "Jalan apa",
  "city": "Kota mana",
  "country": "Negara",
  "province": "Provinsi",
  "postalCode": 112233
}
```

<h3 id="post__contacts_{contact_id}_addresses-parameters">Parameters</h3>

| Name       | In   | Type                                  | Required | Description       |
| ---------- | ---- | ------------------------------------- | -------- | ----------------- |
| contact_id | path | [defaultString](#schemadefaultstring) | true     | Contact ID        |
| body       | body | [addressReq](#schemaaddressreq)       | true     | User Address Data |

> Example responses

> Success Add Address

```json
{
  "message": "User Created",
  "data": {
    "id": "random string for id contact",
    "street": "Jalan apa",
    "city": "Kota mana",
    "country": "Negara",
    "province": "Provinsi",
    "postalCode": 112233
  },
  "meta": {
    "location": "https://api/v1/user/1"
  }
}
```

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="post__contacts_{contact_id}_addresses-responses">Responses</h3>

| Status  | Meaning                                                          | Description  | Schema                          |
| ------- | ---------------------------------------------------------------- | ------------ | ------------------------------- |
| 201     | [Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)     | Created      | [addressRes](#schemaaddressres) |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request  | [error](#schemaerror)           |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized | [error](#schemaerror)           |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden    | [error](#schemaerror)           |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found    | [error](#schemaerror)           |
| default | Default                                                          | OK           | [userRes](#schemauserres)       |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## get__contacts_{contact_id}_addresses

> Code samples

```shell
# You can also use wget
curl -X GET https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses HTTP/1.1
Host: spring.idscript.com
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "name": "firstName or/and lastname",
  "email": "email@email.com",
  "phone": "1234567890"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses',
{
  method: 'GET',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /contacts/{contact_id}/addresses`

*Search Address*

Endpoint for Search Address

> Body parameter

> User Data for Search User Address

```json
{
  "name": "firstName or/and lastname",
  "email": "email@email.com",
  "phone": "1234567890"
}
```

<h3 id="get__contacts_{contact_id}_addresses-parameters">Parameters</h3>

| Name       | In   | Type                                  | Required | Description              |
| ---------- | ---- | ------------------------------------- | -------- | ------------------------ |
| contact_id | path | [defaultString](#schemadefaultstring) | true     | Contact ID               |
| body       | body | [contactReq](#schemacontactreq)       | true     | Search User Address Data |

> Example responses

> Default Contact Response

```json
{
  "message": "Success.",
  "data": {
    "id": "random string for id contact",
    "firstName": "firstName",
    "lastName": "lastName",
    "email": "email@email.com",
    "phone": "1234567890"
  },
  "meta": {
    "location": "http://api/contacts/1"
  }
}
```

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="get__contacts_{contact_id}_addresses-responses">Responses</h3>

| Status  | Meaning                                                          | Description  | Schema                          |
| ------- | ---------------------------------------------------------------- | ------------ | ------------------------------- |
| 200     | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | OK           | [contactRes](#schemacontactres) |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request  | [error](#schemaerror)           |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized | [error](#schemaerror)           |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden    | [error](#schemaerror)           |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found    | [error](#schemaerror)           |
| default | Default                                                          | OK           | [userRes](#schemauserres)       |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## get__contacts_{contact_id}*addresses*{address_id}

> Code samples

```shell
# You can also use wget
curl -X GET https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id} \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id} HTTP/1.1
Host: spring.idscript.com
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /contacts/{contact_id}/addresses/{address_id}`

*Get Address Data*

Endpoint for show Address Data

<h3 id="get__contacts_{contact_id}_addresses_{address_id}-parameters">Parameters</h3>

| Name       | In   | Type                                  | Required | Description |
| ---------- | ---- | ------------------------------------- | -------- | ----------- |
| contact_id | path | [defaultString](#schemadefaultstring) | true     | Contact ID  |
| address_id | path | [defaultString](#schemadefaultstring) | true     | Address ID  |

> Example responses

> Default Contact Response

```json
{
  "message": "Success.",
  "data": {
    "id": "random string for id contact",
    "firstName": "firstName",
    "lastName": "lastName",
    "email": "email@email.com",
    "phone": "1234567890"
  },
  "meta": {
    "location": "http://api/contacts/1"
  }
}
```

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="get__contacts_{contact_id}_addresses_{address_id}-responses">Responses</h3>

| Status  | Meaning                                                          | Description  | Schema                          |
| ------- | ---------------------------------------------------------------- | ------------ | ------------------------------- |
| 200     | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | OK           | [contactRes](#schemacontactres) |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request  | [error](#schemaerror)           |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized | [error](#schemaerror)           |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden    | [error](#schemaerror)           |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found    | [error](#schemaerror)           |
| default | Default                                                          | OK           | [userRes](#schemauserres)       |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## put__contacts_{contact_id}*addresses*{address_id}

> Code samples

```shell
# You can also use wget
curl -X PUT https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id} \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
PUT https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id} HTTP/1.1
Host: spring.idscript.com
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "firstName": "old firstName",
  "lastName": "old lastName",
  "email": "new email@email.com",
  "phone": "old 1234567890"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}',
{
  method: 'PUT',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.put 'https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.put('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /contacts/{contact_id}/addresses/{address_id}`

*Update User Address*

Endpoint for Update User Address

> Body parameter

> User Data for Update User Address

```json
{
  "firstName": "old firstName",
  "lastName": "old lastName",
  "email": "new email@email.com",
  "phone": "old 1234567890"
}
```

<h3 id="put__contacts_{contact_id}_addresses_{address_id}-parameters">Parameters</h3>

| Name       | In   | Type                                  | Required | Description       |
| ---------- | ---- | ------------------------------------- | -------- | ----------------- |
| contact_id | path | [defaultString](#schemadefaultstring) | true     | Contact ID        |
| address_id | path | [defaultString](#schemadefaultstring) | true     | Address ID        |
| body       | body | [contactReq](#schemacontactreq)       | true     | User Address Data |

> Example responses

> Success Add Address

```json
{
  "message": "User Created",
  "data": {
    "id": "random string for id contact",
    "firstName": "firstName",
    "lastName": "lastName",
    "email": "email@email.com",
    "phone": "1234567890"
  },
  "meta": {
    "location": "https://api/v1/user/1"
  }
}
```

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="put__contacts_{contact_id}_addresses_{address_id}-responses">Responses</h3>

| Status  | Meaning                                                          | Description  | Schema                          |
| ------- | ---------------------------------------------------------------- | ------------ | ------------------------------- |
| 200     | [OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)          | Updated      | [contactRes](#schemacontactres) |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request  | [error](#schemaerror)           |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized | [error](#schemaerror)           |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden    | [error](#schemaerror)           |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found    | [error](#schemaerror)           |
| default | Default                                                          | OK           | [userRes](#schemauserres)       |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## delete__contacts_{contact_id}*addresses*{address_id}

> Code samples

```shell
# You can also use wget
curl -X DELETE https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id} \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
DELETE https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id} HTTP/1.1
Host: spring.idscript.com
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.delete 'https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.delete('https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "https://spring.idscript.com/{environment}/v1/contacts/{contact_id}/addresses/{address_id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /contacts/{contact_id}/addresses/{address_id}`

*Delete Address Data*

Endpoint for delete Address Data

<h3 id="delete__contacts_{contact_id}_addresses_{address_id}-parameters">Parameters</h3>

| Name       | In   | Type                                  | Required | Description |
| ---------- | ---- | ------------------------------------- | -------- | ----------- |
| contact_id | path | [defaultString](#schemadefaultstring) | true     | Contact ID  |
| address_id | path | [defaultString](#schemadefaultstring) | true     | Address ID  |

> Example responses

> 400 Bad Request

```json
{
  "error": "Error: your request is invalid."
}
```

> 401 Unauthorized

```json
{
  "error": "Wrong username or password"
}
```

> 403 Forbidden

```json
{
  "error": "Forbidden"
}
```

> 404 Not Found

```json
{
  "error": "Error: not found."
}
```

> Default User Response

```json
{
  "message": "Success.",
  "data": {},
  "meta": {}
}
```

<h3 id="delete__contacts_{contact_id}_addresses_{address_id}-responses">Responses</h3>

| Status  | Meaning                                                          | Description                           | Schema                    |
| ------- | ---------------------------------------------------------------- | ------------------------------------- | ------------------------- |
| 204     | [No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)  | The resource was deleted successfully | None                      |
| 400     | [Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1) | Bad Request                           | [error](#schemaerror)     |
| 401     | [Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)  | Unauthorized                          | [error](#schemaerror)     |
| 403     | [Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)   | Forbidden                             | [error](#schemaerror)     |
| 404     | [Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)   | Not Found                             | [error](#schemaerror)     |
| default | Default                                                          | OK                                    | [userRes](#schemauserres) |

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

# Schemas

<h2 id="tocS_userRes">userRes</h2>
<!-- backwards compatibility -->
<a id="schemauserres"></a>
<a id="schema_userRes"></a>
<a id="tocSuserres"></a>
<a id="tocsuserres"></a>

```json
{
  "message": "string",
  "data": {
    "token": "string",
    "name": "string",
    "username": "string"
  },
  "meta": {
    "location": "string"
  }
}

```

### Properties

| Name       | Type                                  | Required | Restrictions | Description |
| ---------- | ------------------------------------- | -------- | ------------ | ----------- |
| message    | [defaultString](#schemadefaultstring) | false    | none         | none        |
| data       | object                                | false    | none         | none        |
| » token    | [defaultString](#schemadefaultstring) | false    | none         | none        |
| » name     | [defaultString](#schemadefaultstring) | false    | none         | none        |
| » username | [defaultString](#schemadefaultstring) | false    | none         | none        |
| meta       | [meta](#schemameta)                   | false    | none         | none        |

<h2 id="tocS_login">login</h2>
<!-- backwards compatibility -->
<a id="schemalogin"></a>
<a id="schema_login"></a>
<a id="tocSlogin"></a>
<a id="tocslogin"></a>

```json
{
  "username": "string",
  "password": "string"
}

```

### Properties

| Name     | Type                                  | Required | Restrictions | Description |
| -------- | ------------------------------------- | -------- | ------------ | ----------- |
| username | [defaultString](#schemadefaultstring) | false    | none         | none        |
| password | [defaultString](#schemadefaultstring) | false    | none         | none        |

<h2 id="tocS_userUpdate">userUpdate</h2>
<!-- backwards compatibility -->
<a id="schemauserupdate"></a>
<a id="schema_userUpdate"></a>
<a id="tocSuserupdate"></a>
<a id="tocsuserupdate"></a>

```json
{
  "name": "string",
  "password": "string"
}

```

### Properties

| Name     | Type                                  | Required | Restrictions | Description |
| -------- | ------------------------------------- | -------- | ------------ | ----------- |
| name     | [defaultString](#schemadefaultstring) | false    | none         | none        |
| password | [defaultString](#schemadefaultstring) | false    | none         | none        |

<h2 id="tocS_contactReq">contactReq</h2>
<!-- backwards compatibility -->
<a id="schemacontactreq"></a>
<a id="schema_contactReq"></a>
<a id="tocScontactreq"></a>
<a id="tocscontactreq"></a>

```json
{
  "name": "string",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "phone": "string"
}

```

### Properties

| Name      | Type                                  | Required | Restrictions | Description |
| --------- | ------------------------------------- | -------- | ------------ | ----------- |
| name      | [defaultString](#schemadefaultstring) | false    | none         | none        |
| firstName | [defaultString](#schemadefaultstring) | false    | none         | none        |
| lastName  | [defaultString](#schemadefaultstring) | false    | none         | none        |
| email     | [defaultString](#schemadefaultstring) | true     | none         | none        |
| phone     | [defaultString](#schemadefaultstring) | true     | none         | none        |

<h2 id="tocS_addressReq">addressReq</h2>
<!-- backwards compatibility -->
<a id="schemaaddressreq"></a>
<a id="schema_addressReq"></a>
<a id="tocSaddressreq"></a>
<a id="tocsaddressreq"></a>

```json
{
  "street": "string",
  "city": "string",
  "country": "string",
  "province": "string",
  "postalCode": 999999
}

```

### Properties

| Name       | Type                                  | Required | Restrictions | Description |
| ---------- | ------------------------------------- | -------- | ------------ | ----------- |
| street     | [defaultString](#schemadefaultstring) | false    | none         | none        |
| city       | [defaultString](#schemadefaultstring) | false    | none         | none        |
| country    | [defaultString](#schemadefaultstring) | false    | none         | none        |
| province   | [defaultString](#schemadefaultstring) | false    | none         | none        |
| postalCode | integer(int32)                        | false    | none         | none        |

<h2 id="tocS_defaultUserSchema">defaultUserSchema</h2>
<!-- backwards compatibility -->
<a id="schemadefaultuserschema"></a>
<a id="schema_defaultUserSchema"></a>
<a id="tocSdefaultuserschema"></a>
<a id="tocsdefaultuserschema"></a>

```json
{
  "success": true,
  "message": "string",
  "data": {},
  "meta": {}
}

```

### Properties

| Name    | Type                                  | Required | Restrictions | Description |
| ------- | ------------------------------------- | -------- | ------------ | ----------- |
| success | boolean                               | false    | none         | none        |
| message | [defaultString](#schemadefaultstring) | false    | none         | none        |
| data    | object                                | true     | none         | none        |
| meta    | object                                | false    | none         | none        |

<h2 id="tocS_contactRes">contactRes</h2>
<!-- backwards compatibility -->
<a id="schemacontactres"></a>
<a id="schema_contactRes"></a>
<a id="tocScontactres"></a>
<a id="tocscontactres"></a>

```json
{
  "message": "string",
  "data": {
    "id": "string",
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "phone": "string"
  },
  "meta": {
    "location": "string"
  }
}

```

### Properties

| Name        | Type                                  | Required | Restrictions | Description |
| ----------- | ------------------------------------- | -------- | ------------ | ----------- |
| message     | [defaultString](#schemadefaultstring) | false    | none         | none        |
| data        | object                                | false    | none         | none        |
| » id        | [defaultString](#schemadefaultstring) | false    | none         | none        |
| » firstName | [defaultString](#schemadefaultstring) | false    | none         | none        |
| » lastName  | [defaultString](#schemadefaultstring) | false    | none         | none        |
| » email     | [defaultString](#schemadefaultstring) | false    | none         | none        |
| » phone     | [defaultString](#schemadefaultstring) | false    | none         | none        |
| meta        | object                                | false    | none         | none        |
| » location  | [defaultString](#schemadefaultstring) | false    | none         | none        |

<h2 id="tocS_addressRes">addressRes</h2>
<!-- backwards compatibility -->
<a id="schemaaddressres"></a>
<a id="schema_addressRes"></a>
<a id="tocSaddressres"></a>
<a id="tocsaddressres"></a>

```json
{
  "message": "string",
  "data": {
    "id": "string",
    "street": "string",
    "city": "string",
    "country": "string",
    "province": "string",
    "postalCode": 999999
  },
  "meta": {
    "location": "string"
  }
}

```

### Properties

| Name         | Type                                  | Required | Restrictions | Description |
| ------------ | ------------------------------------- | -------- | ------------ | ----------- |
| message      | [defaultString](#schemadefaultstring) | false    | none         | none        |
| data         | object                                | false    | none         | none        |
| » id         | [defaultString](#schemadefaultstring) | false    | none         | none        |
| » street     | [defaultString](#schemadefaultstring) | false    | none         | none        |
| » city       | [defaultString](#schemadefaultstring) | false    | none         | none        |
| » country    | [defaultString](#schemadefaultstring) | false    | none         | none        |
| » province   | [defaultString](#schemadefaultstring) | false    | none         | none        |
| » postalCode | integer(int32)                        | false    | none         | none        |
| meta         | object                                | false    | none         | none        |
| » location   | [defaultString](#schemadefaultstring) | false    | none         | none        |

<h2 id="tocS_defaultString">defaultString</h2>
<!-- backwards compatibility -->
<a id="schemadefaultstring"></a>
<a id="schema_defaultString"></a>
<a id="tocSdefaultstring"></a>
<a id="tocsdefaultstring"></a>

```json
"string"

```

### Properties

| Name        | Type   | Required | Restrictions | Description |
| ----------- | ------ | -------- | ------------ | ----------- |
| *anonymous* | string | false    | none         | none        |

<h2 id="tocS_meta">meta</h2>
<!-- backwards compatibility -->
<a id="schemameta"></a>
<a id="schema_meta"></a>
<a id="tocSmeta"></a>
<a id="tocsmeta"></a>

```json
{
  "location": "string"
}

```

### Properties

| Name     | Type                                  | Required | Restrictions | Description |
| -------- | ------------------------------------- | -------- | ------------ | ----------- |
| location | [defaultString](#schemadefaultstring) | false    | none         | none        |

<h2 id="tocS_error">error</h2>
<!-- backwards compatibility -->
<a id="schemaerror"></a>
<a id="schema_error"></a>
<a id="tocSerror"></a>
<a id="tocserror"></a>

```json
{
  "error": "string"
}

```

### Properties

| Name  | Type                                  | Required | Restrictions | Description |
| ----- | ------------------------------------- | -------- | ------------ | ----------- |
| error | [defaultString](#schemadefaultstring) | false    | none         | none        |
