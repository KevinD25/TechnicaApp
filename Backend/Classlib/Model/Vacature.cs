using Newtonsoft.Json;

namespace Classlib
{
    public class Vacature
    {
        public int id { get; set; }

        public int name { get; set; }

        public int description { get; set; }

        public int link { get; set; }

        public int sponsorid { get; set; }
        public Sponsor sponsor { get; set; }
    }
}