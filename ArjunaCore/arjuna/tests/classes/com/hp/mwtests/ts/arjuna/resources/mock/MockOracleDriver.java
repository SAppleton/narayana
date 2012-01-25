/*
 * JBoss, Home of Professional Open Source
 * Copyright 2006, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 *
 * (C) 2005-2006,
 * @author JBoss Inc.
 */
/*
 * Copyright (C) 2004,
 *
 * Arjuna Technologies Limited,
 * Newcastle upon Tyne,
 * Tyne and Wear,
 * UK.
 *
 * $Id: SyncRecord.java 2342 2006-03-30 13:06:17Z  $
 */

package com.hp.mwtests.ts.arjuna.resources.mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.arjuna.ats.internal.arjuna.objectstore.jdbc.JDBCImple;
import com.arjuna.ats.internal.arjuna.objectstore.jdbc.oracle_driver;


public class MockOracleDriver extends oracle_driver
{
    public MockOracleDriver ()
    {
        super._inUse = new boolean[1];
        super._theConnection = new Connection[1];
        super._preparedStatements = new PreparedStatement[1][];
        
        super._preparedStatements[0] = new PreparedStatement[JDBCImple.STATEMENT_SIZE];
        
        super._preparedStatements[0][JDBCImple.READ_STATE] = new MockPreparedStatement(false);
        super._preparedStatements[0][com.arjuna.ats.internal.arjuna.objectstore.jdbc.JDBCImple.READ_WRITE_SHORTCUT] = new MockPreparedStatement();
        super._preparedStatements[0][com.arjuna.ats.internal.arjuna.objectstore.jdbc.JDBCImple.WRITE_STATE_NEW] = new MockPreparedStatement();
        super._preparedStatements[0][JDBCImple.SELECT_FOR_WRITE_STATE] = new MockPreparedStatement();
        
        super._inUse[0] = false;
        super._theConnection[0] = new MockConnection();
        
        super._isValid = true;
    }
    
    public void setValid (boolean valid)
    {
        super._isValid = valid;
    }
    
    public void resetReadState ()
    {
        super._preparedStatements[0][com.arjuna.ats.internal.arjuna.objectstore.jdbc.JDBCImple.READ_STATE] = new MockPreparedStatement(true);
    }
    
    public void resetReadWriteShortcut ()
    {
        super._preparedStatements[0][JDBCImple.READ_WRITE_SHORTCUT] = new MockPreparedStatement(false);
    }
    
    public void createTable (Statement stmt, String tableName) throws SQLException
    {
        super.createTable(stmt, tableName);
    }

    public int getMaxStateSize()
    {
        return super.getMaxStateSize();
    }
}
