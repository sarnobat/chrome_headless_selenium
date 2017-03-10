sh update_indexes.sh
date
cat urls_not_titled.txt | grep '^http' | uniq | head -$1 | DISPLAY=:99 groovy url2title.groovy  ~/github/chrome_headless/chromedriver_linux64  \
	perl -pe 's{ &amp; }{ & }g' | tee chrome_headless/titles_new.txt | tee -a chrome_headless/titles_all.txt 
