#!/usr/bin/perl
#
use strict;
use warnings;
#perl -pi -e 's/\n//g' bootstrap.css												remove linebreaks
#	\n 				new line

#perl -pi -e 's/\/\*.*?\*\///g' bootstrap.css										remove comments
#	\/\*			/*
#	.*?				any char until the first
#	\*\/			*/

#perl -pi -e 's/((?<=\})|^)\s*<SELECTOR>\s*\{.*?\}//g' 	bootstrap.css				remove selector
#	((?<=\})|^) 	before the match exists either } or the start of the string
#	\s*				any whitespace
#	<SELECTOR>		the selector to find
#	\s*				any whitespace until
#	\}				{
#	.*?				any char until the first
#	\}				}

#perl -pi -e 's/[^\{\}]*\{\s*\}//g' bootstrap.css 									remove any empty selectors, run twice
#	[^\{\}]*		any char that isn't { or } until
#	\{				{
#	\s*				any whitespace until
#	\}				}

#basic structure:
# take in css file and file that is a list of selectors to remove
# remove linebreaks/comments
# remove comments
# for each line in txt
#	remove that line followed by any white space, then { then any chars, then }
# remove any empty selectors, run twice incase nested @media selectors become empty afterwards

# issues this will cause:
# 	comments will be lost 
#	linebreaks will be lost
#	won't handle invalid css at all