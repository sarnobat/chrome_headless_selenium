# If you put debugging statements in here, make sure they go to stderr otherwise your txt output will have junk in it.
echo "$1 :: " $(echo "$1" | /usr/bin/xargs -n 1 -d'\n' timeout 10 lynx -source --dump -useragent L_y_n_x | grep --text 'title' | perl -pe 's{\n}{ }g' | perl -pe 's{.*<title.*?>\s*(.*)\s*</title>.*}{$1}g' | cut -c -200 ) | perl -pe 's{^ :: \n}{}g'
