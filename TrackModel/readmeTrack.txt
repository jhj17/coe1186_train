{\rtf1\ansi\ansicpg1252\cocoartf1343\cocoasubrtf140
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx560\tx1120\tx1680\tx2240\tx2800\tx3360\tx3920\tx4480\tx5040\tx5600\tx6160\tx6720\pardirnatural

\f0\fs24 \cf0 CTC METHODS:\
\
/*\
This method will take line as "red" or "green" (lowercase) and a destination as a station in all caps, e.g. "PIONEER".  It will find the path from the YARD to that station.\
\
It will return an ArrayList<String> that contains information for each block on the way to the station.\
\
"blockNumber, section, blockLength, speed limit" in each string, separated by commas.\
*/\

\b \
public ArrayList<String> getRoute(String line, String destination)\

\b0 \
\
/*\
This method will take line as "red" or "green" (lowercase) and the ID of the the train to be placed as an integer.  The train will be placed on the first block after the YARD.\
\
do NOT use ID = 0\
*/\

\b public void placeTrain(String line, int trainID )\

\b0 \
}