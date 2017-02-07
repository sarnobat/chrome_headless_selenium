# This doesn't work. 
#cat ~/sarnobat.git/yurl_queue_httpcat.txt  | perl -pe 's{.*http}{http}g' | grep -v null | grep -v jpg | DISPLAY=:99 xargs -n 1 -I% groovy headless.groovy ~/github/chrome_headless/chromedriver_linux64 "%"  | grep 'title>' | tee titles.txt
#head -10 ~/sarnobat.git/yurl_queue_httpcat.txt  | perl -pe 's{.*http}{http}g' | grep -v null | grep -v jpg | grep -v "'" | DISPLAY=:99 xargs -n 1 -I% groovy headless.groovy ~/github/chrome_headless/chromedriver_linux64 "%"  | grep 'title>'  | perl -pe 's{.*(<title)}{$1}g'  | perl -pe 's{(</title>).*}{$1}g' | tee titles.txt
#cat titles_all.txt  | perl -pe 's{[\s].*\n}{\n}g' | tee titles_attempted.txt
touch titles_attempted2.txt
cat  ~/sarnobat.git/yurl_queue_httpcat.txt  \
	| grep -v -f titles_attempted2.txt \
	| tail -1 \
	| grep http \
	| perl -pe 's{.*http}{http}g' \
	| tee urls_unattempted.txt \
	| grep -v null \
	| grep -v jpg \
	| grep -v "'" \
	| tee -a titles_attempted2.txt \
	| DISPLAY=:99 groovy title.groovy ~/github/chrome_headless/chromedriver_linux64 \
	| tee titles_new.txt \
	| tee -a titles_all.txt
