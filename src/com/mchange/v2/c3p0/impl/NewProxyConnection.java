/*
 * This class generated by com.mchange.v2.c3p0.codegen.JdbcProxyGenerator$NewProxyConnectionGenerator
 * Sat Sep 29 19:59:09 UTC 2012
 * DO NOT HAND EDIT!!!!
 */
package com.mchange.v2.c3p0.impl;

import com.mchange.v2.c3p0.C3P0ProxyConnection;
import java.lang.String;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.sql.*;
import javax.sql.*;
import com.mchange.v2.log.*;
import java.lang.reflect.Method;
import com.mchange.v2.sql.SqlUtils;
import java.lang.reflect.InvocationTargetException;
import com.mchange.v2.util.ResourceClosedException;

/**
 * This class was generated by com.mchange.v2.c3p0.codegen.JdbcProxyGenerator$NewProxyConnectionGenerator.
 */
public final class NewProxyConnection implements Connection, C3P0ProxyConnection
{
	protected Connection inner;
	
	public NewProxyConnection(Connection inner)
	{ this.inner = inner; }
	
	public synchronized Statement createStatement() throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			Statement innerStmt = inner.createStatement();
			parentPooledConnection.markActiveUncachedStatement( innerStmt );
			return new NewProxyStatement( innerStmt, parentPooledConnection, false, this );
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized Statement createStatement(int a, int b) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			Statement innerStmt = inner.createStatement(a, b);
			parentPooledConnection.markActiveUncachedStatement( innerStmt );
			return new NewProxyStatement( innerStmt, parentPooledConnection, false, this );
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized Statement createStatement(int a, int b, int c) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			Statement innerStmt = inner.createStatement(a, b, c);
			parentPooledConnection.markActiveUncachedStatement( innerStmt );
			return new NewProxyStatement( innerStmt, parentPooledConnection, false, this );
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized PreparedStatement prepareStatement(String a) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			PreparedStatement innerStmt;
			
			if ( parentPooledConnection.isStatementCaching() )
			{
				try
				{
					Class[] argTypes = 
					{
						String.class
					};
					Method method = Connection.class.getMethod( "prepareStatement" , argTypes );
					
					Object[] args = 
					{
						a};
					innerStmt = (PreparedStatement) parentPooledConnection.checkoutStatement( method, args );
					return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, true, this );
				}
				catch (ResourceClosedException e)
				{
					if ( logger.isLoggable( MLevel.FINE ) )
						logger.log( MLevel.FINE, "A Connection tried to prepare a Statement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", e );
					innerStmt = inner.prepareStatement(a);
					parentPooledConnection.markActiveUncachedStatement( innerStmt );
					return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, false, this );
				}
			}
			else
			{
				innerStmt = inner.prepareStatement(a);
				parentPooledConnection.markActiveUncachedStatement( innerStmt );
				return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, false, this );
			}
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized PreparedStatement prepareStatement(String a, int b, int c) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			PreparedStatement innerStmt;
			
			if ( parentPooledConnection.isStatementCaching() )
			{
				try
				{
					Class[] argTypes = 
					{
						String.class,
						int.class,
						int.class
					};
					Method method = Connection.class.getMethod( "prepareStatement" , argTypes );
					
					Object[] args = 
					{
						a,
						new Integer( b ),
						new Integer( c )};
					innerStmt = (PreparedStatement) parentPooledConnection.checkoutStatement( method, args );
					return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, true, this );
				}
				catch (ResourceClosedException e)
				{
					if ( logger.isLoggable( MLevel.FINE ) )
						logger.log( MLevel.FINE, "A Connection tried to prepare a Statement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", e );
					innerStmt = inner.prepareStatement(a, b, c);
					parentPooledConnection.markActiveUncachedStatement( innerStmt );
					return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, false, this );
				}
			}
			else
			{
				innerStmt = inner.prepareStatement(a, b, c);
				parentPooledConnection.markActiveUncachedStatement( innerStmt );
				return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, false, this );
			}
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized PreparedStatement prepareStatement(String a, int b, int c, int d) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			PreparedStatement innerStmt;
			
			if ( parentPooledConnection.isStatementCaching() )
			{
				try
				{
					Class[] argTypes = 
					{
						String.class,
						int.class,
						int.class,
						int.class
					};
					Method method = Connection.class.getMethod( "prepareStatement" , argTypes );
					
					Object[] args = 
					{
						a,
						new Integer( b ),
						new Integer( c ),
						new Integer( d )};
					innerStmt = (PreparedStatement) parentPooledConnection.checkoutStatement( method, args );
					return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, true, this );
				}
				catch (ResourceClosedException e)
				{
					if ( logger.isLoggable( MLevel.FINE ) )
						logger.log( MLevel.FINE, "A Connection tried to prepare a Statement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", e );
					innerStmt = inner.prepareStatement(a, b, c, d);
					parentPooledConnection.markActiveUncachedStatement( innerStmt );
					return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, false, this );
				}
			}
			else
			{
				innerStmt = inner.prepareStatement(a, b, c, d);
				parentPooledConnection.markActiveUncachedStatement( innerStmt );
				return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, false, this );
			}
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized PreparedStatement prepareStatement(String a, int b) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			PreparedStatement innerStmt;
			
			if ( parentPooledConnection.isStatementCaching() )
			{
				try
				{
					Class[] argTypes = 
					{
						String.class,
						int.class
					};
					Method method = Connection.class.getMethod( "prepareStatement" , argTypes );
					
					Object[] args = 
					{
						a,
						new Integer( b )};
					innerStmt = (PreparedStatement) parentPooledConnection.checkoutStatement( method, args );
					return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, true, this );
				}
				catch (ResourceClosedException e)
				{
					if ( logger.isLoggable( MLevel.FINE ) )
						logger.log( MLevel.FINE, "A Connection tried to prepare a Statement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", e );
					innerStmt = inner.prepareStatement(a, b);
					parentPooledConnection.markActiveUncachedStatement( innerStmt );
					return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, false, this );
				}
			}
			else
			{
				innerStmt = inner.prepareStatement(a, b);
				parentPooledConnection.markActiveUncachedStatement( innerStmt );
				return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, false, this );
			}
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized PreparedStatement prepareStatement(String a, int[] b) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			PreparedStatement innerStmt;
			
			if ( parentPooledConnection.isStatementCaching() )
			{
				try
				{
					Class[] argTypes = 
					{
						String.class,
						int[].class
					};
					Method method = Connection.class.getMethod( "prepareStatement" , argTypes );
					
					Object[] args = 
					{
						a,
						b};
					innerStmt = (PreparedStatement) parentPooledConnection.checkoutStatement( method, args );
					return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, true, this );
				}
				catch (ResourceClosedException e)
				{
					if ( logger.isLoggable( MLevel.FINE ) )
						logger.log( MLevel.FINE, "A Connection tried to prepare a Statement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", e );
					innerStmt = inner.prepareStatement(a, b);
					parentPooledConnection.markActiveUncachedStatement( innerStmt );
					return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, false, this );
				}
			}
			else
			{
				innerStmt = inner.prepareStatement(a, b);
				parentPooledConnection.markActiveUncachedStatement( innerStmt );
				return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, false, this );
			}
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized PreparedStatement prepareStatement(String a, String[] b) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			PreparedStatement innerStmt;
			
			if ( parentPooledConnection.isStatementCaching() )
			{
				try
				{
					Class[] argTypes = 
					{
						String.class,
						String[].class
					};
					Method method = Connection.class.getMethod( "prepareStatement" , argTypes );
					
					Object[] args = 
					{
						a,
						b};
					innerStmt = (PreparedStatement) parentPooledConnection.checkoutStatement( method, args );
					return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, true, this );
				}
				catch (ResourceClosedException e)
				{
					if ( logger.isLoggable( MLevel.FINE ) )
						logger.log( MLevel.FINE, "A Connection tried to prepare a Statement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", e );
					innerStmt = inner.prepareStatement(a, b);
					parentPooledConnection.markActiveUncachedStatement( innerStmt );
					return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, false, this );
				}
			}
			else
			{
				innerStmt = inner.prepareStatement(a, b);
				parentPooledConnection.markActiveUncachedStatement( innerStmt );
				return new NewProxyPreparedStatement( innerStmt, parentPooledConnection, false, this );
			}
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized CallableStatement prepareCall(String a) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			CallableStatement innerStmt;
			
			if ( parentPooledConnection.isStatementCaching() )
			{
				try
				{
					Class[] argTypes = 
					{
						String.class
					};
					Method method = Connection.class.getMethod( "prepareCall" , argTypes );
					
					Object[] args = 
					{
						a};
					innerStmt = (CallableStatement) parentPooledConnection.checkoutStatement( method, args );
					return new NewProxyCallableStatement( innerStmt, parentPooledConnection, true, this );
				}
				catch (ResourceClosedException e)
				{
					if ( logger.isLoggable( MLevel.FINE ) )
						logger.log( MLevel.FINE, "A Connection tried to prepare a CallableStatement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", e );
					innerStmt = inner.prepareCall(a);
					parentPooledConnection.markActiveUncachedStatement( innerStmt );
					return new NewProxyCallableStatement( innerStmt, parentPooledConnection, false, this );
				}
			}
			else
			{
				innerStmt = inner.prepareCall(a);
				parentPooledConnection.markActiveUncachedStatement( innerStmt );
				return new NewProxyCallableStatement( innerStmt, parentPooledConnection, false, this );
			}
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized CallableStatement prepareCall(String a, int b, int c) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			CallableStatement innerStmt;
			
			if ( parentPooledConnection.isStatementCaching() )
			{
				try
				{
					Class[] argTypes = 
					{
						String.class,
						int.class,
						int.class
					};
					Method method = Connection.class.getMethod( "prepareCall" , argTypes );
					
					Object[] args = 
					{
						a,
						new Integer( b ),
						new Integer( c )};
					innerStmt = (CallableStatement) parentPooledConnection.checkoutStatement( method, args );
					return new NewProxyCallableStatement( innerStmt, parentPooledConnection, true, this );
				}
				catch (ResourceClosedException e)
				{
					if ( logger.isLoggable( MLevel.FINE ) )
						logger.log( MLevel.FINE, "A Connection tried to prepare a CallableStatement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", e );
					innerStmt = inner.prepareCall(a, b, c);
					parentPooledConnection.markActiveUncachedStatement( innerStmt );
					return new NewProxyCallableStatement( innerStmt, parentPooledConnection, false, this );
				}
			}
			else
			{
				innerStmt = inner.prepareCall(a, b, c);
				parentPooledConnection.markActiveUncachedStatement( innerStmt );
				return new NewProxyCallableStatement( innerStmt, parentPooledConnection, false, this );
			}
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized CallableStatement prepareCall(String a, int b, int c, int d) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			CallableStatement innerStmt;
			
			if ( parentPooledConnection.isStatementCaching() )
			{
				try
				{
					Class[] argTypes = 
					{
						String.class,
						int.class,
						int.class,
						int.class
					};
					Method method = Connection.class.getMethod( "prepareCall" , argTypes );
					
					Object[] args = 
					{
						a,
						new Integer( b ),
						new Integer( c ),
						new Integer( d )};
					innerStmt = (CallableStatement) parentPooledConnection.checkoutStatement( method, args );
					return new NewProxyCallableStatement( innerStmt, parentPooledConnection, true, this );
				}
				catch (ResourceClosedException e)
				{
					if ( logger.isLoggable( MLevel.FINE ) )
						logger.log( MLevel.FINE, "A Connection tried to prepare a CallableStatement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", e );
					innerStmt = inner.prepareCall(a, b, c, d);
					parentPooledConnection.markActiveUncachedStatement( innerStmt );
					return new NewProxyCallableStatement( innerStmt, parentPooledConnection, false, this );
				}
			}
			else
			{
				innerStmt = inner.prepareCall(a, b, c, d);
				parentPooledConnection.markActiveUncachedStatement( innerStmt );
				return new NewProxyCallableStatement( innerStmt, parentPooledConnection, false, this );
			}
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized DatabaseMetaData getMetaData() throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			if (this.metaData == null)
			{
				DatabaseMetaData innerMetaData = inner.getMetaData();
				this.metaData = new NewProxyDatabaseMetaData( innerMetaData, parentPooledConnection, this );
			}
			return this.metaData;
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized void setTransactionIsolation(int a) throws SQLException
	{
		try
		{
			inner.setTransactionIsolation(a);
			parentPooledConnection.markNewTxnIsolation( a );
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized void setCatalog(String a) throws SQLException
	{
		try
		{
			inner.setCatalog(a);
			parentPooledConnection.markNewCatalog( a );
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized void setHoldability(int a) throws SQLException
	{
		try
		{
			inner.setHoldability(a);
			parentPooledConnection.markNewHoldability( a );
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized void setTypeMap(Map a) throws SQLException
	{
		try
		{
			inner.setTypeMap(a);
			parentPooledConnection.markNewTypeMap( a );
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized SQLWarning getWarnings() throws SQLException
	{
		try
		{
			return inner.getWarnings();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized void clearWarnings() throws SQLException
	{
		try
		{
			inner.clearWarnings();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized void rollback() throws SQLException
	{
		try
		{
			txn_known_resolved = true;
			
			inner.rollback();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized void rollback(Savepoint a) throws SQLException
	{
		try
		{
			txn_known_resolved = true;
			
			inner.rollback(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized void setAutoCommit(boolean a) throws SQLException
	{
		try
		{
			txn_known_resolved = true;
			
			inner.setAutoCommit(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized boolean getAutoCommit() throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			return inner.getAutoCommit();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized String getCatalog() throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			return inner.getCatalog();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized int getHoldability() throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			return inner.getHoldability();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized int getTransactionIsolation() throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			return inner.getTransactionIsolation();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized Map getTypeMap() throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			return inner.getTypeMap();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized String nativeSQL(String a) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			return inner.nativeSQL(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized void releaseSavepoint(Savepoint a) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			inner.releaseSavepoint(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized Savepoint setSavepoint() throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			return inner.setSavepoint();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized Savepoint setSavepoint(String a) throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			return inner.setSavepoint(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized void setReadOnly(boolean a) throws SQLException
	{
		try
		{
			inner.setReadOnly(a);
			parentPooledConnection.markNewReadOnly( a );
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized boolean isReadOnly() throws SQLException
	{
		try
		{
			txn_known_resolved = false;
			
			return inner.isReadOnly();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized void close() throws SQLException
	{
		try
		{
			if (! this.isDetached())
			{
				NewPooledConnection npc = parentPooledConnection;
				this.detach();
				npc.markClosedProxyConnection( this, txn_known_resolved );
				this.inner = null;
			}
			else if (Debug.DEBUG && logger.isLoggable( MLevel.FINE ))
			{
				logger.log( MLevel.FINE, this + ": close() called more than once." );
			}
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				if (Debug.DEBUG && logger.isLoggable( MLevel.FINE ))
				{
					logger.log( MLevel.FINE, this + ": close() called more than once." );
				}
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized boolean isClosed() throws SQLException
	{
		try
		{
			return this.isDetached();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	public synchronized void commit() throws SQLException
	{
		try
		{
			txn_known_resolved = true;
			
			inner.commit();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Connection!!!", exc);
			}
			else throw exc;
		}
		catch (Exception exc)
		{
			if (! this.isDetached())
			{
				throw parentPooledConnection.handleThrowable( exc );
			}
			else throw SqlUtils.toSQLException( exc );
		}
	}
	
	boolean txn_known_resolved = true;
	
	DatabaseMetaData metaData = null;
	
	public Object rawConnectionOperation(Method m, Object target, Object[] args)
		throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException
	{
		maybeDirtyTransaction();
		
		if (inner == null)
			throw new SQLException("You cannot operate on a closed Connection!");
		if ( target == C3P0ProxyConnection.RAW_CONNECTION)
			target = inner;
		for (int i = 0, len = args.length; i < len; ++i)
			if (args[i] == C3P0ProxyConnection.RAW_CONNECTION)
				args[i] = inner;
		Object out = m.invoke( target, args );
		
		// we never cache Statements generated by an operation on the raw Connection
		if (out instanceof CallableStatement)
		{
			CallableStatement innerStmt = (CallableStatement) out;
			parentPooledConnection.markActiveUncachedStatement( innerStmt );
			out = new NewProxyCallableStatement( innerStmt, parentPooledConnection, false, this );
		}
		else if (out instanceof PreparedStatement)
		{
			PreparedStatement innerStmt = (PreparedStatement) out;
			parentPooledConnection.markActiveUncachedStatement( innerStmt );
			out = new NewProxyPreparedStatement( innerStmt, parentPooledConnection, false, this );
		}
		else if (out instanceof Statement)
		{
			Statement innerStmt = (Statement) out;
			parentPooledConnection.markActiveUncachedStatement( innerStmt );
			out = new NewProxyStatement( innerStmt, parentPooledConnection, false, this );
		}
		else if (out instanceof ResultSet)
		{
			ResultSet innerRs = (ResultSet) out;
			parentPooledConnection.markActiveRawConnectionResultSet( innerRs );
			out = new NewProxyResultSet( innerRs, parentPooledConnection, inner, this );
		}
		else if (out instanceof DatabaseMetaData)
			out = new NewProxyDatabaseMetaData( (DatabaseMetaData) out, parentPooledConnection );
		return out;
	}
	
	synchronized void maybeDirtyTransaction()
	{ txn_known_resolved = false; }
	private final static MLogger logger = MLog.getLogger( "com.mchange.v2.c3p0.impl.NewProxyConnection" );
	
	volatile NewPooledConnection parentPooledConnection;
	
	ConnectionEventListener cel = new ConnectionEventListener()
	{
		public void connectionErrorOccurred(ConnectionEvent evt)
		{ /* DON'T detach()... IGNORE -- this could be an ordinary error. Leave it to the PooledConnection to test, but leave proxies intact */ }
		
		public void connectionClosed(ConnectionEvent evt)
		{ detach(); }
	};
	
	void attach( NewPooledConnection parentPooledConnection )
	{
		this.parentPooledConnection = parentPooledConnection;
		parentPooledConnection.addConnectionEventListener( cel );
	}
	
	private void detach()
	{
		parentPooledConnection.removeConnectionEventListener( cel );
		parentPooledConnection = null;
	}
	
	NewProxyConnection( Connection inner, NewPooledConnection parentPooledConnection )
	{
		this( inner );
		attach( parentPooledConnection );
	}
	
	boolean isDetached()
	{ return (this.parentPooledConnection == null); }

	public void abort(Executor executor) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Blob createBlob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Clob createClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public NClob createNClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public SQLXML createSQLXML() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Properties getClientInfo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getClientInfo(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNetworkTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getSchema() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isValid(int timeout) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}

	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}

	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setSchema(String schema) throws SQLException {
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
