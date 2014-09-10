#!/usr/bin/perl
#
use strict;
use warnings;
#perl -pi -e 's/\n//g' bootstrap.css					remove linebreaks
#perl -pi -e 's/\/\*.*?\*\///g' bootstrap.css		remove comments
#perl -pi -e 's/ +/ /g' bootstrap.css				everything into single spaces
#perl -pi -e 's/<THING> *?{.*?}//g' bootstrap.css	remove thing

#basic structure:
# take in css file and file that is a list of selectors to remove
# remove linebreaks/comments/extraneous spaces from css
# remove comments/extraneous spaces from txt
# for each line in txt
#	remove that line followed by any white space, then { then any chars, then }

# issues this will cause:
# 	comments will be lost 
#	linebreaks will be lost
#	random spacing will be lost (but not in selectors assuming it's valid CSS)
#	won't handle invalid css at all
#	hard to auto-test