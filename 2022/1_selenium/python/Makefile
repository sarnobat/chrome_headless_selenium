all:	install demo format

cron:	all

demo:
	source ~/trash/selenium/bin/activate
	touch /tmp/jobs.out
	mv --backup=numbered /tmp/jobs.out /tmp/jobs.out.`date -u +%Y-%m-%d__%H:%M`
	~/.pyenv/shims/python helloworld_google_jobs.py > /tmp/jobs.out
# 	cat /tmp/jobs.out | sh format.sh > /tmp/jobs_formatted.out
# 	bbedit /tmp/jobs_formatted.out

install:
	set -e
	python3 -m venv ~/trash/selenium
	sh ~/trash/selenium/bin/activate
	echo $$PATH | tee /tmp/path.txt
	which python | tee /tmp/python.txt
	~/.pyenv/shims/python -m pip install selenium webdriver-manager

format:
	touch /tmp/jobs.tsv
	mv --backup=numbered /tmp/jobs.tsv /tmp/jobs.tsv.`date -u +%Y-%m-%d__%H:%M`
# 	cat /tmp/jobs_formatted.out | sh format.sh | grep -e ': '  | perl -pe 's{title}{\ntitle}g' | rec2csv | tee /tmp/out.csv | csvsql --query "select distinct time,title,company,location from stdin" | csvformat -T  > /tmp/jobs.tsv
	cat /tmp/jobs.out | sh format.sh | grep -e ': '  | perl -pe 's{title}{\ntitle}g' | tee  /tmp/jobs_formatted.out | rec2csv | tee /tmp/out.csv | csvsql --query "select distinct title,location,company from stdin order by company" | csvformat -T | tee /tmp/jobs.tsv
	open 'https://docs.google.com/spreadsheets/d/1vthaeVl8N8jnufDJE8uOlMGaRhurRxDuoKqazx_8_9g/edit?gid=1488891222#gid=1488891222'
	bbedit /tmp/jobs.tsv
# 	bbedit /tmp/out.html