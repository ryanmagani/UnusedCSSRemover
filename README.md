UnusedCSSRemover
=========
A Perl Script to remove CSS 
--------
The purpose of this script is to automatically remove a list of CSS selectors from a given CSS file with an intent of removing unused CSS rules to further minify a file. While this tool cannot find unused CSS rules itself, it will automatically remove any empty CSS rules. Additionally, a list of unused rules can be easily generated with the Audits tab of Chrome Developer Tools.

This tool will not alter any given files, but instead created a new CSS document. As a side-effect of the removal process, all linebreaks and comments will be lost, so it is reccomended that this script is run at the same time as minification and compression.

* Usage:  
`./unused_css_remover.pl [Original CSS File] [Text file] [New CSS File]`
  * Original CSS File: the CSS file from which selectors will be removed, this file will NOT be modified
  * A new-line separated list of selectors to remove from the file
  * New CSS File: the file path/name of the modified file to create
* To get a list of unused CSS rules by your webpage (using Chrome):
  1. Open your webpage using Chrome
  2. Open the Chrome Developer Tools window (https://developer.chrome.com/devtools)
  3. Open the "Audits" tab
  4. Select the "Web Page Performance" checkbox if it is not already selected
  5. Click the "Run" button
  6. Click on the audit in the "Results" column if it did not automatically open
  7. Use the arrow to expand the "Remove unused CSS rules (#)" section
  8. Use the arrow to expand the "Your_CSS_Filename.css:" section
  9. Copy and paste the selectors into a text file
* Issues:
  * Selectors containing nested-child selectors cannot be removed directly, ex:  
`@media plan { .anyClass { color: green; }}`  
They will, however, be removed if all of their child selectors are removed
  * This script makes no guarantees for syntactically incorrect CSS files
  * All comments and linebreaks are removed in the copy of the CSS file created
