"# PracticeApp" 

Application created using following technology details

Architecture - MVVM + Clean

Language - Java + Kotlin

Unit Tests Coverage done for Repository, Usecase & Viewmodel

Contains 2 screens, loads data reading from api https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/default/dynamodb-writer

First screen shows the list of Listings data

Second screen shows the detail listings on click action on first screen

Has a separate module for image caching built using DiskLruCache by Jake Wharton to do the heavy lifting internally
