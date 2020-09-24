using Newtonsoft.Json;

namespace Classlib
{
    public class Vacature
    {
        public int id { get; set; }

        //TODO : ADD VACATURE INFO 


        public int sponsorid { get; set; }
        public Sponsor sponsor { get; set; }
    }
}