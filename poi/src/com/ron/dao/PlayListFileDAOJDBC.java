package com.ron.dao;

import static com.ron.dao.DAOUtil.close;
import static com.ron.dao.DAOUtil.setValues;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ron.exceptions.DAOException;
import com.ron.model.PlayListFile;
import com.ron.model.PlayListFilePIC;

public class PlayListFileDAOJDBC extends BaseDAOJDBC implements PlayListFileDAO {
	public static Logger log = Logger.getLogger(PlayListFileDAOJDBC.class);

	private static final String SQL_INSERT_PLAYLISTFILE = "insert into xxfb_playlistfile (id, listid, seq, filename, uuid, type, duration) values (playlistfile_id_seq.nextval, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_LIST_ORDER_BY_ID = "select * from xxfb_playlistfile t where t.listid = ? order by t.seq";
	private static final String SQL_DELETE_PLAYLISTFILE = "delete xxfb_playlistfile t where t.id = ?";
	private static final String SQL_LISTPIC_ORDER_BY_ID = "select p.listid p_username, p.type p_type, p.uuid p_uuid, c.uuid c_uuid, p.filename p_filename, c.filename c_filename, p.duration p_duration, c.duration c_duration from xxfb_playlistfile p, xxfb_container c where p.uuid = c.uuid(+) and p.listid = ? order by p.filename, c.filename";
	
	@Override
	public void create(List<PlayListFile> list) throws IllegalArgumentException,
			DAOException {
	    Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_PLAYLISTFILE);
            int i = 0;
            for(PlayListFile p:list){
        	    Object[] values = {
        	    		p.getListid(), list.size() - i, p.getFilename(), p.getUuid(), p.getType(), p.getDuration()
        	    };
        	    setValues(preparedStatement, values);
                preparedStatement.addBatch();
                i++;
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
		
	}
	
	@Override
	public void destroy(List<PlayListFile> list) throws IllegalArgumentException,
			DAOException {
	    Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_PLAYLISTFILE);
            for(PlayListFile p:list){
        	    Object[] values = {
        	    		p.getId()
        	    };
        	    setValues(preparedStatement, values);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
		
	}

	  @Override
	    public List<PlayListFile> list(String username) throws DAOException {
	  	    Object[] values = {
	  	    	username	
		    };
	  	    
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<PlayListFile> p = new ArrayList<PlayListFile>();

	        try {
	            connection = daoFactory.getConnection();
	            preparedStatement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
        	    setValues(preparedStatement, values);
	            resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {
	                p.add(map(resultSet));
	            }
	        } catch (SQLException e) {
	            throw new DAOException(e);
	        } finally {
	            close(connection, preparedStatement, resultSet);
	        }

	        return p;
	    }
		
	    private static PlayListFile map(ResultSet resultSet) throws SQLException {
	        PlayListFile playListFile = new PlayListFile();

	        playListFile.setListid(resultSet.getString("listid"));
	        playListFile.setSeq(resultSet.getInt("seq"));
	        playListFile.setFilename(resultSet.getString("filename"));
	        playListFile.setUuid(resultSet.getString("uuid"));
	        playListFile.setId(resultSet.getString("Id"));
	        playListFile.setType(resultSet.getString("Type"));
	        
	        return playListFile;
	    }

		@Override
		public List<PlayListFilePIC> listpic(String id) {
			 Object[] values = {
			  	    	id	
				    };
			  	    
			        Connection connection = null;
			        PreparedStatement preparedStatement = null;
			        ResultSet resultSet = null;
			        List<PlayListFilePIC> p = new ArrayList<PlayListFilePIC>();

			        try {
			            connection = daoFactory.getConnection();
			            preparedStatement = connection.prepareStatement(SQL_LISTPIC_ORDER_BY_ID);
		        	    setValues(preparedStatement, values);
			            resultSet = preparedStatement.executeQuery();
			            while (resultSet.next()) {
			                p.add(mappic(resultSet));
			            }
			        } catch (SQLException e) {
			            throw new DAOException(e);
			        } finally {
			            close(connection, preparedStatement, resultSet);
			        }

			        return p;
		}
		
		
		 private static PlayListFilePIC mappic(ResultSet resultSet) throws SQLException {
		        PlayListFilePIC playListFilePIC = new PlayListFilePIC();

		        playListFilePIC.setP_username(resultSet.getString("p_username"));
		        playListFilePIC.setP_type(resultSet.getString("p_type"));
		        playListFilePIC.setP_uuid(resultSet.getString("p_uuid"));
		        playListFilePIC.setC_uuid(resultSet.getString("c_uuid"));
		        playListFilePIC.setP_filename(resultSet.getString("p_filename"));
		        playListFilePIC.setC_filename(resultSet.getString("c_filename"));
		        playListFilePIC.setP_duration(resultSet.getString("p_duration"));
		        playListFilePIC.setC_duration(resultSet.getString("c_duration"));
		        
		        return playListFilePIC;
		    }
	    
	    

}
