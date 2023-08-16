cd ~/github/chrome_headless/2_native/
cat  ~/db.git/yurl_queue_httpcat.txt ~/db.git/yurl_flatfile_db/yurl_queue_2022.txt  | grep http  | perl -pe 's{[0-9]+::[0-9]+::http}{http}g' | sort | grep -i -v '\.jpg' | grep -i -v '\.gif' | grep -i -v '\.png' | tee ~/github/chrome_headless/tmp/urls_httpcat.txt >/dev/null
cat ~/db.git/yurl_flatfile_db/titles_all.txt  | perl -pe 's{ :: .*}{}g' | tee ~/github/chrome_headless/tmp/titles_obtained.txt   >/dev/null


sort ~/github/chrome_headless/tmp/urls_httpcat.txt    | uniq > ~/github/chrome_headless/tmp/urls_httpcat_sorted_uniq.txt
sort ~/github/chrome_headless/tmp/titles_obtained.txt | uniq > ~/github/chrome_headless/tmp/titles_obtained_sorted_uniq.txt
comm -2 -3 ~/github/chrome_headless/tmp/urls_httpcat_sorted_uniq.txt ~/github/chrome_headless/tmp/titles_obtained_sorted_uniq.txt > ~/github/chrome_headless/tmp/urls_not_titled.txt
