#!/bin/bash

APP_NAME="{{ cookiecutter.app_name }}"
REPO_URL="{{ cookiecutter.repo_url }}"
REPO_URL_TRIMMED="$(echo -e "${REPO_URL}" | sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//')"

if [[ $REPO_URL_TRIMMED == "Enter the repo url" || $REPO_URL_TRIMMED == "" ]]
then
    echo -e "\n\n\n****************** You have not provided the repo url, now you have to setup git manually  ******************\n\n\n"
else
    echo -e "\n\n\n****************** Initialising Git ******************\n\n\n"
    git init
    git remote add origin $REPO_URL
    git add --all
    git commit -am "initial commit"
    echo -e "\n\n\n****************** Pushing initial code to master ******************\n\n\n"
    git push -f origin master
    #generating all relevant branches as well
    echo -e "\n\n\n****************** Generating all relevant branches as well ******************\n\n\n"
    git checkout -b staging
    git checkout -b develop
    #develop at last so you land on develop branch
    echo -e "\n\n\n****************** Pushing code in all relevant branches ******************\n\n\n"
    git push -f origin staging
    git push -f origin develop
fi


echo -e "\n\n\n******************"
echo -e "!!!!!!!!!!!!!!!!!!!!! IMPORTANT NOTE !!!!!!!!!!!!!!!!!!!!!"
echo -e "Check for (TODO) section in Android Studio's bottom bar and read all the TODO comments in every file and take action according to your preference.
After that delete all the todo comments, clean the project and then run it."
echo -e "******************\n\n\n"

echo -e "\n\n\n************************* $APP_NAME setup completed -> Happy coding :) *************************\n\n"

if [ "$OSTYPE" == "msys" ]
then
$SHELL
fi
