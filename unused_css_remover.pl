#!/usr/bin/perl
# A script to remove empty and unused selectors from a CSS file
# As a side effect of these operations, all comments and linebreaks are removed from the
	# file, it is therefore recommended to run this script when minifying/compressing the file
# This script is not a comprehensive minifier or compressor
# This script makes no guarantees for syntactically incorrect CSS files
# Attempting to remove a selector with nested elements (essentially the @media tag) will not
	# work properly, however, if all of the child selectors are removed from the parent, then the
	# parent will be removed

# Usage:
# ./unused_css_remover.pl [Original CSS File] [Text file] [New CSS File]
#		Original CSS File: the CSS file from which selectors will be removed, this file will NOT be modified
#		A \n separated list of selectors to remove from the file
#		New CSS File: the file path/name of the modified file to create

use strict;
use warnings;
use File::Slurp;

# If there aren't 3 arguments, die
my $numberOfArguments = $#ARGV + 1;
if ($numberOfArguments != 3)
{
	die "Usage: unused_css_remover.pl [CSS_FILE_TO_REMOVE_FROM] [TXT_SELECTORS_TO_REMOVE] [CSS_FILE_TO_CREATE]";
}

# Read the arguments
my $cssFileName = $ARGV[0];
my $txtFileName = $ARGV[1];
my $newCSS = $ARGV[2];

# Read the CSS contents
my $cssContent = read_file("$cssFileName");

# Read the text contents into a string
my $txtContent = read_file("$txtFileName");

# Remove comments from CSS - see end of file
$txtContent =~ s/\/\*.*?\*\///g;

# Split the text into an array of selectors
my @selectors = split("\n", $txtContent);

# Remove new lines from CSS - see end of file
$cssContent =~ s/\n//g;
# Remove comments from CSS - see end of file
$cssContent =~ s/\/\*.*?\*\///g;

# Remove all the selectors from the text file
foreach (@selectors)
{
	# see end of file
	$cssContent =~ s/((?<=\})|(?<=\{)|^)\s*\Q$_\E\s*\{.*?\}//g;
}

# Remove all empty selectors (any selector with only whitespace between it's curly braces)
# perform this action twice as the @media tag may contain only empty selectors which will be
# removed with the first call leaving the empty @media tag if not run twice
$cssContent =~ s/[^\{\}]*\{\s*\}//g;
$cssContent =~ s/[^\{\}]*\{\s*\}//g;

# Write the altered CSS content into the chosen filename
write_file("$newCSS", "$cssContent");

# Regex explanations
# s/\n//g												remove linebreaks
#	\n 		 		new line

# s/\/\*.*?\*\///g										remove comments
#	\/\*			/*
#	.*?				any char until the first
#	\*\/			*/

# s/((?<=\})|(?<=\{)|^)\s*\Q$_\E\s*\{.*?\}//g				remove selector
#	((?<=\})|(?<=\{)|^) 	before the match exists either }, { or the start of the string
#	\s*				any whitespace
#	\Q$_\E			the selector to find ($_) wrapped in \Q\E literal string quotes
#	\s*				any whitespace until
#	\}				{
#	.*?				any char until the first
#	\}				}

# s/[^\{\}]*\{\s*\}//g									remove any empty selectors, run twice
#	[^\{\}]*		any char that isn't { or } until
#	\{				{
#	\s*				any whitespace until
#	\}				}