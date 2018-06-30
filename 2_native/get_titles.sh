#-----------------------------------------------------------------------------
#
# DATE
#     2018 Jun 29
#
# NOTES
#     After chrome released --dump-dom for command line, we don't need docker or selenium.
#
#-----------------------------------------------------------------------------
cd ~/github/chrome_headless/2_native/

sh update_indexes.sh

COUNT=${1:-2}

cat tmp/urls_not_titled.txt | uniq | tail -$COUNT | tee tmp/urls_attempted.txt \
	| xargs -n 1 -d'\n' url2titleandurl.sh \
	| tee tmp/titles_new.txt \
	| tee -a ~/sarnobat.git/db/yurl_flatfile_db/titles_all.txt
