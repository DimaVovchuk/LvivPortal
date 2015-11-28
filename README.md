# Empty django project template
[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy?template=https://github.com/wowkin2/django-project-template)

After deployment need to execute command `manage.py syncdb`
and create superuser with admin permissions

# Local work
To have this project working locally,
create new file named 'local_settings.py' in folder 'webapp' with following content:
```python
import os


BASE_DIR = os.path.dirname(os.path.dirname(__file__))

STATICFILES_DIRS = (
    os.path.join(BASE_DIR, "static"),
)
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': os.path.join(BASE_DIR, 'db.sqlite3'),
    }
}

DEBUG = True
```
