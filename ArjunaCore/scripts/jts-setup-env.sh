# JBoss, Home of Professional Open Source
# Copyright 2006, Red Hat Middleware LLC, and individual contributors
# as indicated by the @author tags.
# See the copyright.txt in the distribution for a
# full listing of individual contributors.
# This copyrighted material is made available to anyone wishing to use,
# modify, copy, or redistribute it subject to the terms and conditions
# of the GNU Lesser General Public License, v. 2.1.
# This program is distributed in the hope that it will be useful, but WITHOUT A
# WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
# PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
# You should have received a copy of the GNU Lesser General Public License,
# v.2.1 along with this distribution; if not, write to the Free Software
# Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
# MA  02110-1301, USA.
#
# (C) 2005-2006,
# @author JBoss Inc.
#
# Arjuna Technologies Ltd.
# Copyright 2004
#

CONTINUE_SETUP=true

if test "x$JAVA_HOME" = "x"
then

	echo Please ensure the JAVA_HOME environment variable is set
	CONTINUE_SETUP=false

else

	if test "x$NARAYANA_HOME" = "x"
	then

		echo Please ensure the NARAYANA_HOME environment variable is set
		CONTINUE_SETUP=false

	fi

fi

if test "$CONTINUE_SETUP" = "true"
then

# Full JacORB installation location
# Caution: JBossTS needs a specially patched version of JacORB.
# Use $JBOSSTS_HOME/jacorb here unless you have a good reason not to.
#
isIdlj=0

if [ $isIdlj == 1 ]; then
	JACORB_HOME=
else
	JACORB_HOME=$NARAYANA_HOME/jacorb
fi

# Find classpath separator

CPS=":"

case `uname -a` in
    CYGWIN_* | Windows* )
        CPS=";"
	if [ $isIdlj == 0 ]; then
		JACORB_HOME=`echo $JACORB_HOME | sed -e 's;\\\;/;g'`
	fi
	NARAYANA_HOME=`echo $NARAYANA_HOME | sed -e 's;\\\;/;g'`
    ;;
esac

# Setup EXT classpath

echo Setting up environment

PRODUCT_CLASSPATH="$NARAYANA_HOME/lib/jts/narayana-jts.jar"
PRODUCT_CLASSPATH="$PRODUCT_CLASSPATH$CPS$NARAYANA_HOME/etc/"


for i in $NARAYANA_HOME/lib/ext/*.jar
do
EXT_CLASSPATH="$EXT_CLASSPATH$CPS$i"
done

if [ $isIdlj == 0 ]; then
	for i in $JACORB_HOME/lib/*.jar
	do
		JACORB_CLASSPATH="$JACORB_CLASSPATH$CPS$i"
	done
	JACORB_CLASSPATH="$JACORB_CLASSPATH$CPS$JACORB_HOME/etc"

	CLASSPATH=".$CPS$PRODUCT_CLASSPATH$CPS$EXT_CLASSPATH$CPS$JACORB_CLASSPATH"
else
	CLASSPATH=".$CPS$PRODUCT_CLASSPATH$CPS$EXT_CLASSPATH"
fi

export CLASSPATH

fi
