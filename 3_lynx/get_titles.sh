#-----------------------------------------------------------------------------
#
# DATE
#     2019 Sep 04
#
# NOTES
#     
#
#-----------------------------------------------------------------------------

which lynx || sudo apt install lynx

cd ~/github/chrome_headless/3_lynx/

sh update_indexes.sh

COUNT=${1:-2}
echo $PWD
cat /tmp/urls_not_titled.txt | uniq | tail -$COUNT | tee /tmp/urls_attempted.txt \
	| xargs -n 1 -d'\n' sh ~/github/chrome_headless/3_lynx/url2titleandurl.sh \
	| tee /tmp/titles_new.txt \
	| tee -a ~/sarnobat.git/db/yurl_flatfile_db/titles_all.txt
