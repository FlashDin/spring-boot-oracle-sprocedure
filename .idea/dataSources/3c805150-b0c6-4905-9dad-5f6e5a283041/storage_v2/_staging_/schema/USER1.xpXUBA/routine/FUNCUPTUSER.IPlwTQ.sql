create function funcContohJa(p_id       IN TABLE_USER.ID%TYPE,
                                       p_username IN TABLE_USER.USERNAME%TYPE,
                                       p_password IN TABLE_USER.PASSWORD%TYPE)
  return varchar
is result varchar(100);
  begin
    update TABLE_USER
    set USERNAME = p_username,
        PASSWORD = p_password
    where ID = p_id;
    result := p_username || ' ' || p_password || ' Update Row ' || ' ' || TO_CHAR(SQL%ROWCOUNT);
    return (result);
    exception when others
    then
      result := 'Update gagal';
      return (result);
  end;
/

