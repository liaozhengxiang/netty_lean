namespace java thrift.gen

typedef i16 short
typedef i32 int
typedef bool boolean
typedef i64 long
typedef string String

struct Person{
    1:optional String username,
    2:optional int age,
    3:optional boolean married
}

exception DataException{
      1: optional String message,
      2: optional String callStack,
      3: optional String date
}

service PersonService{

 Person getByUserName(1:required String userName) throws (1:DataException dataException),
 void savePerson(1: required Person person)

}

