/*
 * This class generated by com.mchange.v2.c3p0.codegen.JdbcProxyGenerator$NewProxyAnyStatementGenerator
 * Sat Sep 29 19:59:09 UTC 2012
 * DO NOT HAND EDIT!!!!
 */
package com.mchange.v2.c3p0.impl;

import com.mchange.v2.c3p0.C3P0ProxyStatement;
import com.mchange.v2.c3p0.impl.ProxyResultSetDetachable;
import java.lang.String;
import java.sql.*;
import javax.sql.*;
import com.mchange.v2.log.*;
import java.lang.reflect.Method;
import com.mchange.v2.sql.SqlUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class was generated by com.mchange.v2.c3p0.codegen.JdbcProxyGenerator$NewProxyAnyStatementGenerator.
 */
public final class NewProxyStatement implements Statement, C3P0ProxyStatement, ProxyResultSetDetachable
{
	protected Statement inner;
	
	public NewProxyStatement(Statement inner)
	{ this.inner = inner; }
	
	public final SQLWarning getWarnings() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.getWarnings();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final void clearWarnings() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			inner.clearWarnings();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final void addBatch(String a) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			inner.addBatch(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final void clearBatch() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			inner.clearBatch();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int[] executeBatch() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.executeBatch();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final ResultSet executeQuery(String a) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			ResultSet innerResultSet = inner.executeQuery(a);
			if (innerResultSet == null) return null;
			parentPooledConnection.markActiveResultSetForStatement( inner, innerResultSet );
			NewProxyResultSet out = new NewProxyResultSet( innerResultSet, parentPooledConnection, inner, this );
			synchronized ( myProxyResultSets ) { myProxyResultSets.add( out ); }
			return out;
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int executeUpdate(String a) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.executeUpdate(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int executeUpdate(String a, int b) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.executeUpdate(a, b);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int executeUpdate(String a, int[] b) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.executeUpdate(a, b);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int executeUpdate(String a, String[] b) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.executeUpdate(a, b);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int getFetchDirection() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.getFetchDirection();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int getFetchSize() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.getFetchSize();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final ResultSet getGeneratedKeys() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			ResultSet innerResultSet = inner.getGeneratedKeys();
			if (innerResultSet == null) return null;
			parentPooledConnection.markActiveResultSetForStatement( inner, innerResultSet );
			NewProxyResultSet out = new NewProxyResultSet( innerResultSet, parentPooledConnection, inner, this );
			synchronized ( myProxyResultSets ) { myProxyResultSets.add( out ); }
			return out;
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int getMaxFieldSize() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.getMaxFieldSize();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int getMaxRows() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.getMaxRows();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final boolean getMoreResults() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.getMoreResults();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final boolean getMoreResults(int a) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.getMoreResults(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int getQueryTimeout() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.getQueryTimeout();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final ResultSet getResultSet() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			ResultSet innerResultSet = inner.getResultSet();
			if (innerResultSet == null) return null;
			parentPooledConnection.markActiveResultSetForStatement( inner, innerResultSet );
			NewProxyResultSet out = new NewProxyResultSet( innerResultSet, parentPooledConnection, inner, this );
			synchronized ( myProxyResultSets ) { myProxyResultSets.add( out ); }
			return out;
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int getResultSetConcurrency() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.getResultSetConcurrency();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int getResultSetHoldability() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.getResultSetHoldability();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int getResultSetType() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.getResultSetType();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final int getUpdateCount() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.getUpdateCount();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final void setCursorName(String a) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			inner.setCursorName(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final void setEscapeProcessing(boolean a) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			inner.setEscapeProcessing(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final void setFetchDirection(int a) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			inner.setFetchDirection(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final void setFetchSize(int a) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			inner.setFetchSize(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final void setMaxFieldSize(int a) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			inner.setMaxFieldSize(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final void setMaxRows(int a) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			inner.setMaxRows(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final void setQueryTimeout(int a) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			inner.setQueryTimeout(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final void close() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			if (! this.isDetached())
			{
				synchronized ( myProxyResultSets )
				{
					for( Iterator ii = myProxyResultSets.iterator(); ii.hasNext(); )
					{
						ResultSet closeMe = (ResultSet) ii.next();
						ii.remove();
						
						try { closeMe.close(); }
						catch (SQLException e)
						{
							if (logger.isLoggable( MLevel.WARNING ))
								logger.log( MLevel.WARNING, "Exception on close of apparently orphaned ResultSet.", e);
						}
						if (logger.isLoggable( MLevel.FINE ))
							logger.log( MLevel.FINE, this + " closed orphaned ResultSet: " +closeMe);
					}
				}
				
				if ( is_cached )
					parentPooledConnection.checkinStatement( inner );
				else
				{
					parentPooledConnection.markInactiveUncachedStatement( inner );
					try{ inner.close(); }
					catch (Exception e )
					{
						if (logger.isLoggable( MLevel.WARNING ))
							logger.log( MLevel.WARNING, "Exception on close of inner statement.", e);
						SQLException sqle = SqlUtils.toSQLException( e );
						throw sqle;
					}
				}
				
				this.detach();
				this.inner = null;
				this.creatorProxy = null;
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
	
	public final boolean execute(String a) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.execute(a);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final boolean execute(String a, int b) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.execute(a, b);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final boolean execute(String a, int[] b) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.execute(a, b);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final boolean execute(String a, String[] b) throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			return inner.execute(a, b);
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final void cancel() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			inner.cancel();
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	public final Connection getConnection() throws SQLException
	{
		try
		{
			maybeDirtyTransaction();
			
			if (! this.isDetached())
				return creatorProxy;
			else
				throw new SQLException("You cannot operate on a closed Statement!");
		}
		catch (NullPointerException exc)
		{
			if ( this.isDetached() )
			{
				throw SqlUtils.toSQLException("You can't operate on a closed Statement!!!", exc);
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
	
	private final static MLogger logger = MLog.getLogger( "com.mchange.v2.c3p0.impl.NewProxyStatement" );
	
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
	
	NewProxyStatement( Statement inner, NewPooledConnection parentPooledConnection )
	{
		this( inner );
		attach( parentPooledConnection );
	}
	
	boolean isDetached()
	{ return (this.parentPooledConnection == null); }
	
	boolean is_cached;
	NewProxyConnection creatorProxy;
	
	// Although formally unnecessary, we sync access to myProxyResultSets on
	// that set's own lock, in case clients (illegally but not uncommonly) close()
	// the Statement from a Thread other than the one they use in general
	// with the Statement
	HashSet myProxyResultSets = new HashSet();
	
	public void detachProxyResultSet( ResultSet prs )
	{
		synchronized (myProxyResultSets) { myProxyResultSets.remove( prs ); }
	}
	
	NewProxyStatement( Statement inner, NewPooledConnection parentPooledConnection, boolean cached, NewProxyConnection cProxy )
	{
		this( inner, parentPooledConnection );
		this.is_cached = cached;
		this.creatorProxy = cProxy;
	}
	
	public Object rawStatementOperation(Method m, Object target, Object[] args) throws IllegalAccessException, InvocationTargetException, SQLException
	{
		maybeDirtyTransaction();
		
		if (target == C3P0ProxyStatement.RAW_STATEMENT) target = inner;
		for (int i = 0, len = args.length; i < len; ++i)
			if (args[i] == C3P0ProxyStatement.RAW_STATEMENT) args[i] = inner;
		Object out = m.invoke(target, args);
		if (out instanceof ResultSet)
		{
			ResultSet innerResultSet = (ResultSet) out;
			parentPooledConnection.markActiveResultSetForStatement( inner, innerResultSet );
			out = new NewProxyResultSet( innerResultSet, parentPooledConnection, inner, this );
		}
		
		return out;
	}
	
	void maybeDirtyTransaction()
	{ creatorProxy.maybeDirtyTransaction(); }

	public void closeOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public boolean isCloseOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isPoolable() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public void setPoolable(boolean poolable) throws SQLException {
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
