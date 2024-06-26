public class EmployeeDaoImpl implements EmployeeDao{
    @Override
    public void create(String client, EmployeeDo obj) throws Exception {
        //create a new row
        System.out.println("Created new row in the Employee table");
    }

    @Override
    public void delete(String client, int employeeID) throws Exception {
     //delete a row
        System.out.println("Deleted a row with EmployeeID: "+employeeID);
    }

    @Override
    public EmployeeDo get(String client, int employeeId) throws Exception {
        //fetch row
        System.out.println("Fetching Data from the DB");
        return new EmployeeDo();
    }
}
