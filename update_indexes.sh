cd ~/github/chrome_headless/
cat  ~/sarnobat.git/yurl_queue_httpcat.txt  | grep http  | perl -pe 's{[0-9]+::[0-9]+::http}{http}g' | sort | grep -i -v '\.jpg' | grep -i -v '\.gif' | grep -i -v '\.png' | tee ~/github/chrome_headless/urls_httpcat.txt >/dev/null
cat ~/github/chrome_headless/titles_all.txt  | perl -pe 's{ :: .*}{}g' | tee titles_obtained.txt   >/dev/null

# TODO: this performs very badly. Use diff instead of grep -v -f
#nice cat ~/github/chrome_headless/urls_httpcat.txt | nice grep -v -f ~/github/chrome_headless/titles_obtained.txt | tee urls_not_titled.txt >/dev/null

sort ~/github/chrome_headless/urls_httpcat.txt    | uniq > ~/github/chrome_headless/urls_httpcat_sorted_uniq.txt
sort ~/github/chrome_headless/titles_obtained.txt | uniq > ~/github/chrome_headless/titles_obtained_sorted_uniq.txt
comm -2 -3 ~/github/chrome_headless/urls_httpcat_sorted_uniq.txt ~/github/chrome_headless/titles_obtained_sorted_uniq.txt > urls_not_titled.txt
