date
sh update_indexes.sh
date
cat urls_not_titled.txt | grep '^http' | uniq | head -$1 | xargs -n 1 ../url2titleandurl.sh   \
	| perl -pe 's{ &amp; }{ & }g' | tee chrome_headless/titles_new.txt | tee -a chrome_headless/titles_all.txt 

cat ~/github/chrome_headless/titles/titles.txt | grep -i -v timeout  | shuf | perl -pe 's{(.*) :: (.*)}{<a href="$1">$2</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}g' | tee ~/www/yurl/ticker/content.html
