cat <(echo "$1 :: ") <(echo "$1" | xargs -n 1 -d'\n' google-chrome --headless --dump-dom --disable-gpu | grep '<title' | perl -pe 's{.*<title>\s*(.*)\s*</title>.*}{$1}g')
