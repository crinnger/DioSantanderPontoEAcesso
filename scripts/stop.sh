#!/bin/bash
ps -ef | grep DioPontoEAcesso-V1.jar | grep -v grep | awk '{print $2}' | xargs kill