cd ~/github/chrome_headless/2_native/

sh update_indexes.sh

cat tmp/urls_not_titled.txt | uniq | tail -2 | tee tmp/urls_attempted.txt \
	| xargs -n 1 -d'\n' url2titleandurl.sh \
	| tee tmp/titles_new.txt \
	| tee -a ~/sarnobat.git/db/yurl_flatfile_db/titles_all.txt
#	| DISPLAY=:99 groovy title.groovy ~/github/chrome_headless/chromedriver_linux64 \
