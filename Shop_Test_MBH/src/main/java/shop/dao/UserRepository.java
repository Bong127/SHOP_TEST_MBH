package shop.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;
import shop.dto.PersistentLogin;
import shop.dto.User;

public class UserRepository extends JDBConnection {
	
	public User map(ResultSet rs) throws Exception {
		User user = new User();
    	user.setId( rs.getString("id")); 
    	user.setPassword( rs.getString("password")); 
    	user.setName( rs.getString("name")); 
    	user.setGender( rs.getString("gender")); 
    	user.setBirth( rs.getString("birth")); 
    	user.setMail( rs.getString("mail")); 
    	user.setPhone( rs.getString("phone")); 
    	user.setAddress( rs.getString("address")); 
    	user.setRegistDay( rs.getString("regist_day")); 
		return user;
	}
	
	
	/**
	 * 회원 등록
	 * @param user
	 * @return
	 */
	public int insert(User user) throws Exception{
		 int result = 0;
	        StringBuilder sql = new StringBuilder("INSERT INTO user (");
	        StringBuilder placeholders = new StringBuilder(" VALUES (");
	        
	        Field[] fields = user.getClass().getDeclaredFields();
	        boolean first = true;

	        for (Field field : fields) {
	            field.setAccessible(true);
	            Object value = field.get(user);

	            if (value != null) {
	                if (!first) {
	                    sql.append(", ");
	                    placeholders.append(", ");
	                }
					String fieldName = field.getName();
	                sql.append(fieldName);
	                placeholders.append("?");
	                first = false;
	            }
	        }

	        sql.append(") ");
	        placeholders.append(")");
	        sql.append(placeholders.toString());
	        
	        try {
	            psmt = con.prepareStatement(sql.toString());
	            int index = 1;

	            for (Field field : fields) {
	                field.setAccessible(true);
	                Object value = field.get(user);

	                if (value != null) {
	                    if (value instanceof String) {
	                        psmt.setString(index++, (String) value);
	                    } else if (value instanceof Boolean) {
	                        psmt.setBoolean(index++, (Boolean) value);
	                    } else if (value instanceof Long) {
	                        psmt.setLong(index++, (Long) value);
	                    } else if (value instanceof Integer) {
	                        psmt.setInt(index++, (Integer) value);
	                    } else if (value instanceof Date) {
	                        psmt.setDate(index++, new java.sql.Date(((Date) value).getTime()));
	                    } else {
	                        psmt.setObject(index++, value);
	                    }
	                }
	            }
	            result = psmt.executeUpdate();
	        } catch (Exception e) {
	            System.err.println("user - insert 도중 에러");
	            e.printStackTrace();
	        }
	        return result;
	}
	
	
	/**
	 * 로그인을 위한 사용자 조회
	 * @param id
	 * @param pw
	 * @return
	 * @throws Exception 
	 */
	public User login(String id, String pw) throws Exception {
		String sql = "SELECT * FROM user WHERE id = ? and password = ?";
	    User user = null;
	    try {
	        psmt = con.prepareStatement(sql);
	        psmt.setString(1, id);
	        psmt.setString(2, pw);

	        rs = psmt.executeQuery();
	        if(rs.next())
	        	user = map(rs);
	        rs.close();
	    } catch (SQLException e) {
	        System.err.println("로그인 실패!");
	        e.printStackTrace();
	    }
	    return user;
	}
	
	
	
	
	/**
	 * 로그인을 위한 사용자 조회
	 * @param id
	 * @param pw
	 * @return
	 * @throws Exception 
	 */
	public User getUserById(String id) throws Exception {
	    String sql = "SELECT * FROM user WHERE id = ?";
	    
	    User user = null;
	    try {
	        psmt = con.prepareStatement(sql);
	        psmt.setString(1, id);

	        rs = psmt.executeQuery();
	        if(rs.next())
	        	user = map(rs);
	        rs.close();
	    } catch (SQLException e) {
	        System.err.println("로그인 정보 조회 중, 에러 발생!");
	        e.printStackTrace();
	    }
	    return user;
	}
	
	
	/**
	 * 회원 수정
	 * @param user
	 * @return
	 */
	public int update(User user) throws Exception {
        int result = 0;
        StringBuilder sql = new StringBuilder("UPDATE user SET ");
        StringBuilder whereClause = new StringBuilder(" WHERE id = ?");

        Field[] fields = user.getClass().getDeclaredFields();
        boolean first = true;
        Object pkValue = null;

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(user);
            if (field.getName().equals("id")) {
                pkValue = value;
                continue;
            }
            if (value != null) {
                if (!first) {
                    sql.append(", ");
                }
                String fieldName = field.getName();
                sql.append(fieldName).append(" = ?");
                first = false;
            }
        }
        sql.append(whereClause);
        try {
            psmt = con.prepareStatement(sql.toString());
            int index = 1;

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(user);
                if (field.getName().equals("id")) {
                    continue;
                }
                if (value != null) {
                    if (value instanceof String) {
                        psmt.setString(index++, (String) value);
                    } else if (value instanceof Boolean) {
                        psmt.setBoolean(index++, (Boolean) value);
                    } else if (value instanceof Long) {
                        psmt.setLong(index++, (Long) value);
                    } else if (value instanceof Integer) {
                        psmt.setInt(index++, (Integer) value);
                    } else if (value instanceof Double) {
                        psmt.setDouble(index++, (Double) value);
                    } else if (value instanceof Float) {
                        psmt.setFloat(index++, (Float) value);
                    } else if (value instanceof java.util.Date) {
                        psmt.setDate(index++, new java.sql.Date(((java.util.Date) value).getTime()));
                    } else {
                        psmt.setObject(index++, value);
                    }
                }
            }

            // WHERE pk = ? <-- 조건 값 지정
            if (pkValue instanceof String) {
                psmt.setString(index, (String) pkValue);
            } else if (pkValue instanceof Boolean) {
                psmt.setBoolean(index, (Boolean) pkValue);
            } else if (pkValue instanceof Long) {
                psmt.setLong(index, (Long) pkValue);
            } else if (pkValue instanceof Integer) {
                psmt.setInt(index, (Integer) pkValue);
            } else if (pkValue instanceof Double) {
                psmt.setDouble(index, (Double) pkValue);
            } else if (pkValue instanceof Float) {
                psmt.setFloat(index, (Float) pkValue);
            } else if (pkValue instanceof java.util.Date) {
                psmt.setDate(index, new java.sql.Date(((java.util.Date) pkValue).getTime()));
            } else {
                psmt.setObject(index, pkValue);
            }
            result = psmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("user - update 도중 에러");
            e.printStackTrace();
        }
        return result;
	}


	/**
	 * 회원 삭제
	 * @param id
	 * @return
	 */
	public int delete(String id) {
		int result = 0;
		String sql = "delete from user where id = ?";
		try {
			psmt = con.prepareStatement(sql);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("user - delete 도중 에러");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 토큰 리프레쉬
	 * @param userId
	 */
	public String refreshToken(String userId) {
	    PersistentLogin persistentLogin = selectToken(userId);
	    String token = null;
	    if (persistentLogin == null) {
	        // 토큰이 없는 경우, 삽입
	    	token = insertToken(userId);
	    } else {
	        // 토큰이 있는 경우, 갱신
	    	token =  updateToken(userId);
	    }
	    return token;
	}

	
	
	/**
	 * 토큰 정보 조회
	 * @param userId
	 * @return
	 */
	public PersistentLogin selectToken(String userId) {
	    String sql = "SELECT * FROM persistent_logins WHERE user_id = ?";
	    
	    PersistentLogin persistentLogin = null;
	    try {
	        psmt = con.prepareStatement(sql);
	        psmt.setString(1, userId);

	        rs = psmt.executeQuery();
	        if (rs.next()) {
	        	persistentLogin = new PersistentLogin();
	        	persistentLogin.setpNo( rs.getInt("p_no")); 
	        	persistentLogin.setUserId( rs.getString("user_id") ); 
	        	persistentLogin.setToken( rs.getString("token") ); 
	        	persistentLogin.setDate( rs.getTimestamp("token") ); 
	        }
	        rs.close();
	    } catch (SQLException e) {
	        System.err.println("자동 로그인 정보 조회 중, 에러 발생!");
	        e.printStackTrace();
	    }
	    return persistentLogin;
	}
	
	
	/**
	 * 토큰 정보 조회 - 토큰으로
	 * @param token
	 * @return
	 */
	public PersistentLogin selectTokenByToken(String token) {
	    String sql = "SELECT * FROM persistent_logins WHERE token = ?";
	    
	    PersistentLogin persistentLogin = null;
	    try {
	        psmt = con.prepareStatement(sql);
	        psmt.setString(1, token);

	        rs = psmt.executeQuery();
	        if (rs.next()) {
	            persistentLogin = new PersistentLogin();
	            persistentLogin.setpNo(rs.getInt("p_no")); 
	            persistentLogin.setUserId(rs.getString("user_id")); 
	            persistentLogin.setToken(rs.getString("token")); 
	            persistentLogin.setDate(rs.getTimestamp("date")); // date 필드로 변경
	        }
	        rs.close();
	    } catch (SQLException e) {
	        System.err.println("자동 로그인 정보 조회 중, 에러 발생!");
	        e.printStackTrace();
	    }
	    return persistentLogin;
	}


	
	
	/**
	 * 자동 로그인 토큰 생성
	 * @param userId
	 * @return
	 */
	public String insertToken(String userId) {
		 int result = 0;
	    String sql = "INSERT INTO persistent_logins (user_id, token) VALUES (?, ?)";
	    String token = UUID.randomUUID().toString();
	    try {
	        psmt = con.prepareStatement(sql);
	        psmt.setString(1, userId);
	        psmt.setString(2, token);

	        result = psmt.executeUpdate(); // 퍼시스턴트 로그인 정보 등록 요청
	    } catch (SQLException e) {
	        System.err.println("자동 로그인 정보 등록 중, 에러 발생!");
	        e.printStackTrace();
	    }
	    System.out.println("자동 로그인 정보 " + result + "개가 등록되었습니다.");
	    return token;
	}
	
	/**
	 * 자동 로그인 토큰 갱신
	 * @param userId
	 * @return
	 */
	public String updateToken(String userId) {
	    int result = 0;
	    String sql = "UPDATE persistent_logins SET token = ?, date = now() WHERE user_id = ?";
	    String token = UUID.randomUUID().toString();
	    try {
	    	psmt = con.prepareStatement(sql);
	        psmt.setString(1, token);
	        psmt.setString(2, userId);

	        result = psmt.executeUpdate(); // 퍼시스턴트 로그인 정보 수정 요청
	    } catch (SQLException e) {
	        System.err.println("자동 로그인 정보 수정 중, 에러 발생!");
	        e.printStackTrace();
	    }
	    System.out.println("자동 로그인 정보 " + result + "개의 데이터가 수정되었습니다.");
	    return token;
	}
	
	
	/**
	 * 토큰 삭제
	 * - 로그아웃 시, 자동 로그인 풀림
	 * @param userId
	 * @return
	 */
	public int deleteToken(String userId) {
	    int result = 0;
	    String sql = "DELETE FROM persistent_logins WHERE user_id = ?";
	    
	    try {
	        psmt = con.prepareStatement(sql);
	        psmt.setString(1, userId);

	        result = psmt.executeUpdate(); // 특정 사용자의 자동 로그인 정보 삭제 요청
	    } catch (SQLException e) {
	        System.err.println("자동 로그인 정보 삭제 중, 에러 발생!");
	        e.printStackTrace();
	    }
	    System.out.println("자동 로그인 정보 " + result + "개의 데이터가 삭제되었습니다.");
	    return result;
	}

}

















