cd ~/github/chrome_headless/
cat  ~/sarnobat.git/yurl_queue_httpcat.txt  | grep http  | perl -pe 's{.*http}{http}g' | sort | grep -i -v '\.jpg' | grep -i -v '\.gif' | grep -i -v '\.png' | tee ~/github/chrome_headless/urls_httpcat.txt >/dev/null
cat titles_all.txt  | perl -pe 's{ :: .*}{}g' | tee titles_obtained.txt   >/dev/null
cat urls_httpcat.txt | grep -v -f titles_obtained.txt | tee urls_not_titled.txt >/dev/null
