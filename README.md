# GitHub Activity CLI

## Description

Just a demo project to see how the program could run from the terminal building a single command line interface using the GitHub API.

As requirements I couldn't use a library or framework to make the fetches so I learned and managed how to make them using Java and HttpClient.

To parse the JSON I used the json-simple JAR that you can get [here](https://code.google.com/archive/p/json-simple/downloads).

## How to run

First thing first, in any of the options below you need to make sure the jar is in the lib dir (or wherever you have the jar) and to build the project.
Go straight to your working directory and then copy:

```bash
   java -cp out/production/GitHubActivity;lib/json-simple-1.1.1.jar
```

Then have two options to run the programm:

1. Main (name of the main class) and then it's gonna require the username or
2. Main <username> directly.

[ProjectURL](https://github.com/oligarc/GitCli)
