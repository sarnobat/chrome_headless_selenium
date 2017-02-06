#!/bin/bash
read URL
TITLE=`echo $URL | DISPLAY=:99 groovy headless.groovy ~/github/chrome_headless/chromedriver_linux64   | tee out.txt   | perl -pe 's{<title>\s*}{<title>}g' | grep --text '<title>' | tee -a title_lines.txt | perl -pe 's{.*<title>\s*(.*?)</title>.*}{$1}g'`
echo "$URL :: $TITLE"
