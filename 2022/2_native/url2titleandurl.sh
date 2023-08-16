# If you put debugging statements in here, make sure they go to stderr otherwise your txt output will have junk in it.
echo "$1 :: " $(echo "$1" | /usr/bin/xargs -n 1 -d'\n' timeout 10 /usr/bin/google-chrome --headless --dump-dom --disable-gpu | grep '<title' | perl -pe 's{.*<title>\s*(.*)\s*</title>.*}{$1}g')
