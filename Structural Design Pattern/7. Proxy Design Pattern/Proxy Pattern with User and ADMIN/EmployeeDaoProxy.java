public class EmployeeDaoProxy implements EmployeeDao{
    EmployeeDao employeeDao;

    public EmployeeDaoProxy() {
        this.employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public void create(String client, EmployeeDo obj) throws Exception {
        if(client.equals("ADMIN")){
            employeeDao.create(client,obj);
            return;
        }
        throw new Exception("Access Denied");
    }

    @Override
    public void delete(String client, int employeeID) throws Exception {
        if(client.equals("ADMIN")){
            employeeDao.delete(client,employeeID);
            return;
        }
        throw new Exception("Access Denied");
    }

    @Override
    public EmployeeDo get(String client, int employeeId) throws Exception {
        if(client.equals("ADMIN")||client.equals("USER")){
           return employeeDao.get(client,employeeId);

        }
        throw new Exception("Access Denied");
    }
}
