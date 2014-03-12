/*
 * Distributed as part of c3p0 v.0.9.2-pre5
 *
 * Copyright (C) 2012 Machinery For Change, Inc.
 *
 * Author: Steve Waldman <swaldman@mchange.com>
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 2.1, as 
 * published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; see the file LICENSE.  If not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 */


package com.mchange.v2.c3p0.impl;

import java.io.InputStream;
import java.io.Reader;
import java.sql.*;
import java.util.Map;
import java.util.Set;
import com.mchange.v2.sql.filter.FilterResultSet;

final class SnatchFromSetResultSet extends FilterResultSet
{
    Set activeResultSets;

    SnatchFromSetResultSet(Set activeResultSets)
    { this.activeResultSets = activeResultSets; }

    public synchronized void setInner(ResultSet inner)
    {
	this.inner = inner;
	activeResultSets.add( inner );
    }
    
    public synchronized void close() throws SQLException
    { 
	inner.close();
	activeResultSets.remove( inner ); 
	inner = null;
    }

	public int getHoldability() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Reader getNCharacterStream(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Reader getNCharacterStream(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public NClob getNClob(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public NClob getNClob(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNString(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNString(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public RowId getRowId(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public RowId getRowId(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public SQLXML getSQLXML(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public SQLXML getSQLXML(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateClob(int columnIndex, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateClob(String columnLabel, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateNClob(int columnIndex, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateNClob(String columnLabel, Reader reader) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateNString(int columnIndex, String nString) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateNString(String columnLabel, String nString) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateRowId(int columnIndex, RowId x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateRowId(String columnLabel, RowId x) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
