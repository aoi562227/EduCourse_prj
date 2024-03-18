package eduCourse_prj.student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eduCourse_prj.DbConnection;
import eduCourse_prj.VO.CrsRegVO;

public class CrsRegDAO {
	
	private static CrsRegDAO crDAO;
	
	private CrsRegDAO() {
	}
	
	public static CrsRegDAO getInstance() {
		if(crDAO == null) {
			crDAO = new CrsRegDAO();
		} // end if
		return crDAO;
	} // getInstance
	
	/**
	 * DB에서 로그인한 학생의 수강신청 가능 과목을 가져오는 method
	 * @param stdnt_number
	 * @return
	 * @throws SQLException
	 */
	public List<CrsRegVO> slctAllCrsReg(int stdnt_number) throws SQLException {
		List<CrsRegVO> listCrsRegVO = new ArrayList<CrsRegVO>();
		CrsRegVO crVO = null;
		DbConnection dbCon = DbConnection.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String id = "scott";
			String pass = "tiger";
			
			con = dbCon.getConnection(id, pass);
			
			String selectAllCrsReg = "select d.dept_name, c.course_name, c.course_code, l.lect_room, l.capacity, c.credit_hours "
									+ "from dept d "
									+ "join student s on s.dept_code = d.dept_code "
									+ "right outer join course c on c.dept_code = d.dept_code "
									+ "join lecture l on l.course_code = c.course_code "
									+ "where s.std_number = ?";
			pstmt = con.prepareStatement(selectAllCrsReg);
			pstmt.setInt(1, stdnt_number);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				crVO = new CrsRegVO(rs.getString("dept_name"), rs.getString("course_name"), rs.getString("course_code"), rs.getString("lect_room"), rs.getInt("capacity"), rs.getInt("credit_hours"));
				listCrsRegVO.add(crVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end finally
		
		return listCrsRegVO;
	} // slctAllCrsReg

} // CrsRegDAO