All services used by the widgets

Preferences Service
  - store user data in text files on service location
  - private/non-shared information
  - encrypted store
    - guid, user, type, data
    - file: guid.type

Key Store Service
  - store key-value(s) in database table
  - public/shared information with option to restrict view
  - user/role/group/public views
  - key-value pair store
    - key, occurance, value, restriction

Database Service
  - action, type, filters
    - action = crud
    - type = mapped capability
    - filters = where clause
