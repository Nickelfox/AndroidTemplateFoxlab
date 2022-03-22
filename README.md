
# Android Project Template

_Use this template project to improve your project setup experience._

![Nickelfox](https://media-exp1.licdn.com/dms/image/C4D1BAQG8ZmDiozD2Cw/company-background_10000/0/1642578713018?e=2147483647&v=beta&t=Ykkeyj6Mctkar_PdYS64usWqFbgBE6ePZPv7SnS53eg "Nickelfox")

*Template contents:*

* recommended package structure
* latest libraries (Androidx, Glide, Retrofit, Moshi, A3 libraries, Timber, LeakCanary)
* flavor dimension for development environments (`dev`, `staging`, `prod`)


## Project Setup (using this template)

```
projectgen gen --github-url git@github.com:Nickelfox/AndroidTemplateFoxlab.git
```

```
Please install ruby, python3, cookiecutter and projectgen, in your system.

cookiecutter installation guide: https://cookiecutter.readthedocs.io/en/latest/installation.html

projectgen installation command: gem install projectgen
```

Supply the following values when prompted:
Note: Only name and repo_url is required

1) `name`        - name of cloned directory  (eg   myproject-android)
2) `app_name`         - Leave blank to use the previous input `name`
3) `package_name`     - It will be generated automatically using the `name`
4) `package_name_dir` - It will be generated automatically using the `name`
5) `repo_url`      - Add your github/bitbucket repo url here


