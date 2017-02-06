#!/bin/bash
read URL
TITLE=`echo $URL \
	| DISPLAY=:99 groovy headless.groovy ~/github/chrome_headless/chromedriver_linux64   \
	| tee out.txt   \
	| perl -pe 's{<title>\s*}{<title>}g' \
	| grep --text '<title>' \
	| tee title_line.txt \
	| tee -a title_lines.txt \
	| perl -pe 's{.*<title>\s*(.*?)</title>.*}{$1}g' \
	| tee titles_greedy.txt` 
echo "$URL :: $TITLE"
