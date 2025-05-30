all:	version	demo

version:
	"/Volumes/apps/Apps/Google Chrome.app/Contents/MacOS/Google Chrome" --version

demo:
	 "/Volumes/apps/Apps/Google Chrome.app/Contents/MacOS/Google Chrome"  --headless --disable-gpu   --dump-dom  https://www.chromestatus.com | tee /tmp/out.html
	 #open https://www.chromestatus.com
	 bbedit /tmp/out.html