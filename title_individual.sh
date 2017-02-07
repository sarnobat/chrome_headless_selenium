#!/bin/bash

DISPLAY=:99 groovy headless.groovy ~/github/chrome_headless/chromedriver_linux64   \
	| tee out.txt   \
	| perl -pe 's{<title>\s*}{<title>}g' \
	| grep --line-buffered --text '<title>' \
	| tee title_line.txt \
	| tee -a title_lines.txt \
        | sed -u 's/.*<title>\(.*\)<.title>.*/\1/g' \
	| tee title_sed.txt 
