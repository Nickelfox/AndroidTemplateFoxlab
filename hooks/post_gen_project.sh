#!/bin/bash

APP_NAME="{{ cookiecutter.app_name }}"
REPO_URL="{{ cookiecutter.repo_url }}"


if [[ $USE_ANALYTICS == "Enter the repo url" ]]
then
    echo "\n\n\n****************** You have not provided the repo url, now you have to setup git manually  ******************\n\n\n"
else
    echo "\n\n\n****************** Initialising Git ******************\n\n\n"
    git init
    git remote add origin $REPO_URL
    git add --all
    git commit -am "initial commit"
    echo "\n\n\n****************** Pushing initial code to master ******************\n\n\n"
    git push -f origin master
    #generating all relevant branches as well
    echo "\n\n\n****************** Generating all relevant branches as well ******************\n\n\n"
    git checkout -b staging
    git checkout -b develop
    #develop at last so you land on develop branch
    echo "\n\n\n****************** Pushing code in all relevant branches as well ******************\n\n\n"
    git push -f origin staging
    git push -f origin develop
fi


echo "\n\n\n******************"
echo "Important Note!!!!!!"
echo "Check for (TODO) section in Android Studio's bottom bar and read all the TODO comments in every file and take action according to your preference.
After that delete all the todo comments, clean the project and then run it."


echo -e "\n\n\n************************* $APP_NAME setup completed -> Happy coding :) *************************\n\n"


