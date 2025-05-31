cat <<'EOF' > /tmp/format.awk
BEGIN {
	PREV_LINE_WAS_TITLE = 0
}
/title.*/ {
	PREV_LINE_WAS_TITLE = 1
	print($0)
	next
}
/.*/ {
	if (PREV_LINE_WAS_TITLE) {
		print("company: " $0)
	} else {	
		print($0)
	}
	PREV_LINE_WAS_TITLE = 0
}
EOF

cat - \
	| perl -pe  's{^(.*(engineer|java|developer|manager).*)$}{title: $1}i' \
	| perl -pe  's{(.*)•( via.*)}{location: $1\nprovider: $2}' \
	| perl -pe  's{^(.*(full|part).*time.*)$}{time: $1}i' \
	| perl -pe  's{^(.* ago.*)$}{date: $1}i' \
	| perl -pe  's{^(.* hour.*)$}{salary: $1}i' \
	| perl -pe  's{^(.* year.*)$}{salary: $1}i' \
	| perl -pe  's{}{}' \
	| perl -pe  's{}{}' \
	| perl -pe  's{}{}' \
	| perl -pe  's{}{}' \
	| perl -pe  's{}{}' \
	| perl -pe  's{}{}' \
	| grep -v -e insurance \
	| grep -v -e degree \
	| grep -v -e Paid \
	| grep -v -i -e entry \
	| grep -v -i -e junior \
	| grep -v -i -e architect \
	| grep -v -i -e react \
	| awk -f /tmp/format.awk \
	| tee /tmp/jobs_formatted1.txt
	



# 	| perl -pe  's{^(.*(dice).*)$}{provider: $1}i' \
# 	| perl -pe  's{^(.*, [A-Z]{2} .*)$}{location: $1}i' \

# 	| perl -pe  's{^(.*(systems|inc\b|bank|service).*)$}{company: $1}i' \	
# 	| perl -pe 's/^.$/----------------/'	\